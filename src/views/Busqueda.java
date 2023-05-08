package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.DatosHotel;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;//Reserva
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private DatosHotel hotel;

    public Busqueda() throws SQLException, ClassNotFoundException {
        hotel = new DatosHotel();
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);
        //Reservas
        mostrarcontenido();
        ///-----------------------
        tbReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tbReservas.getSelectedRow(); // primero, obtengo la fila seleccionada
                int columna = tbReservas.getSelectedColumn(); // luego, obtengo la columna seleccionada
                String num_reserva = String.valueOf(tbReservas.getValueAt(fila, 0));
                String fecha_entrada = String.valueOf(tbReservas.getValueAt(fila, 1));
                String fecha_salida = String.valueOf(tbReservas.getValueAt(fila, 2));
                String valor = String.valueOf(tbReservas.getValueAt(fila, 3));
                String forma_pago = String.valueOf(tbReservas.getValueAt(fila, 4));
                reservacionDetalles detalles = new reservacionDetalles(num_reserva, fecha_entrada, fecha_salida, valor, forma_pago);
                detalles.setVisible(true);
            }
        });

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);//Huespedes--------------------------
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        ///Huespedes
        mostrarhuespedes();
        //-----------------------------
        tbHuespedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tbHuespedes.getSelectedRow(); // primero, obtengo la fila seleccionada
                int columna = tbHuespedes.getSelectedColumn(); // luego, obtengo la columna seleccionada
                String num_huesped = String.valueOf(tbHuespedes.getValueAt(fila, 0));
                String nombre = String.valueOf(tbHuespedes.getValueAt(fila, 1));
                String apellido = String.valueOf(tbHuespedes.getValueAt(fila, 2));
                String fecha_nacimiento = String.valueOf(tbHuespedes.getValueAt(fila, 3));
                String nacionalidad = String.valueOf(tbHuespedes.getValueAt(fila, 4));
                String telefono = String.valueOf(tbHuespedes.getValueAt(fila, 5));
                String num_reserva = String.valueOf(tbHuespedes.getValueAt(fila, 6));
                DetallesHuesped detalles = new DetallesHuesped(num_huesped, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, num_reserva);
                detalles.setVisible(true);
            }
        });

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
                try {
                    mostrarcontenido();
                } catch (SQLException ex) {
                    // Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    // Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println(txtBuscar.getText());
                    busqueda(txtBuscar.getText());///Espera un dato
                } catch (ClassNotFoundException ex) {
                  //  Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    //Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("Refrescar");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                while (modelo.getRowCount() > 0) {
                    modelo.removeRow(0);
                }
                while (modeloHuesped.getRowCount() > 0) {
                    modeloHuesped.removeRow(0);
                }
                try {
                    mostrarcontenido();
                    mostrarhuespedes();
                } catch (SQLException ex) {

                } catch (ClassNotFoundException ex) {
                }
            }
        });

    }

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void mostrarcontenido() throws SQLException, ClassNotFoundException {
        ResultSet resultado = hotel.reservas();
        while (resultado.next()) {
            Object[] fila = new Object[5];
            fila[0] = resultado.getInt("id");
            fila[1] = resultado.getString("fecha_entrada");
            fila[2] = resultado.getString("fecha_Salida");
            fila[3] = resultado.getString("valor");
            fila[4] = resultado.getString("forma_de_pago");
            modelo.addRow(fila);
        }
    }

    private void mostrarhuespedes() throws SQLException, ClassNotFoundException {
        ResultSet resultadohuesped = hotel.huespedes();
        while (resultadohuesped.next()) {
            Object[] filah = new Object[7];
            filah[0] = resultadohuesped.getInt("id");
            filah[1] = resultadohuesped.getString("nombre");
            filah[2] = resultadohuesped.getString("apellido");
            filah[3] = resultadohuesped.getString("fecha_de_naciemiento");
            filah[4] = resultadohuesped.getString("nacionalidad");
            filah[5] = resultadohuesped.getString("telefono");
            filah[6] = resultadohuesped.getInt("id_reserva");
            modeloHuesped.addRow(filah);
        }
    }

    private void busqueda(String dato) throws ClassNotFoundException, SQLException {
        DatosHotel datos = new DatosHotel();
        mostrarDatosBusqueda(datos.busquedaHuespedes(dato), datos.busquedaReservas(dato));
    }

    private void mostrarDatosBusqueda(ResultSet huesped,ResultSet reserva) throws SQLException {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        while (modeloHuesped.getRowCount() > 0) {
            modeloHuesped.removeRow(0);
        }
        while (huesped.next()) {
            Object[] filah = new Object[7];
            filah[0] = huesped.getInt("id");
            filah[1] = huesped.getString("nombre");
            filah[2] = huesped.getString("apellido");
            filah[3] = huesped.getString("fecha_de_naciemiento");
            filah[4] = huesped.getString("nacionalidad");
            filah[5] = huesped.getString("telefono");
            filah[6] = huesped.getInt("id_reserva");
            modeloHuesped.addRow(filah);
        }
        while (reserva.next()) {
            Object[] fila = new Object[5];
            fila[0] = reserva.getInt("id");
            fila[1] = reserva.getString("fecha_entrada");
            fila[2] = reserva.getString("fecha_Salida");
            fila[3] = reserva.getString("valor");
            fila[4] = reserva.getString("forma_de_pago");
            modelo.addRow(fila);
        }

    }

}
