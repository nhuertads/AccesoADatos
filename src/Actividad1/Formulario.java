package Actividad1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Formulario extends JFrame {

  private JPanel contentPane;
  private JTextField tf1;
  private JTextField tf2;
  private JLabel labelResultado;
  private JButton btnConsultaPorCdigo;
  private JLabel lblIngreseCdigoDe;
  private JTextField tf3;

  /**
   * Launch the application.
   */
  
  // Escribimos el main, que es lo que se va a ejecutar cuando le demos a run.
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Formulario frame = new Formulario();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }


  public Formulario() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 606, 405);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    //Creamos el botón Descripcion del articulo y le damos unos valores para posicionarlo en la pantalla
    JLabel lblDescripcinDelArtculo = new JLabel("Descripción del artículo:");
    lblDescripcinDelArtculo.setBounds(23, 38, 193, 14);
    contentPane.add(lblDescripcinDelArtculo);
    
    tf1 = new JTextField();
    tf1.setBounds(247, 35, 193, 20);
    contentPane.add(tf1);
    tf1.setColumns(10);
    
    //Creamos el botón Precio, con el setBounds para posicionarlo en la pantalla
    JLabel lblPrecio = new JLabel("Precio:");
    lblPrecio.setBounds(23, 74, 95, 14);
    contentPane.add(lblPrecio);
    
    tf2 = new JTextField();
    tf2.setBounds(247, 71, 107, 20);
    contentPane.add(tf2);
    tf2.setColumns(10);
    
    //Creamos la consulta Alta, que será un botón, conectado a nuestra base de datos llamada bd1
    JButton btnAlta = new JButton("Alta");
    btnAlta.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        labelResultado.setText("");        
        try {
          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd1","root" ,"");
          Statement comando=conexion.createStatement();
          comando.executeUpdate("insert into articulos(descripcion,precio) values ('"+tf1.getText()+"',"+tf2.getText()+")");
          conexion.close();
          labelResultado.setText("se registraron los datos");
          tf1.setText("");
          tf2.setText("");
        } catch(SQLException ex){
          setTitle(ex.toString());
        }
      }
    });
    //código para posicionar el botón Alta
    btnAlta.setBounds(20, 220, 180, 23);
    contentPane.add(btnAlta);
    
    //Creamos la linea de texto resultado, con su respectivo posicionamiento.
    labelResultado = new JLabel("resultado");
    labelResultado.setBounds(361, 122, 229, 14);
    contentPane.add(labelResultado);

    
    tf3 = new JTextField();
    tf3.setBounds(247, 123, 86, 20);
    contentPane.add(tf3);
    tf3.setColumns(10);
 
    //Creamos el botón borrar, conectada a bd1
    JButton btnNewButton = new JButton("Borrar");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        labelResultado.setText("");
        try {
          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd1","root" ,"");
          Statement comando=conexion.createStatement();
          int cantidad = comando.executeUpdate("delete from articulos where codigo="+tf3.getText());
          //Codigo donde analiza si el numero introducido existe en (Codigos) y según exista o no, te imprimirá un mensaje o el otro.
          if (cantidad==1) {
            tf1.setText("");
            tf2.setText("");        
            labelResultado.setText("Se borro el artículo con dicho código");
          } else {
            labelResultado.setText("No existe un artículo con dicho código");
          }
          conexion.close();
        } catch(SQLException ex){
          setTitle(ex.toString());
        }        
      }
    });
    btnNewButton.setBounds(24, 156, 177, 23);
    contentPane.add(btnNewButton);
    
    //Creamos el botón Modificar, conectado a bd1
    JButton btnNewButton_1 = new JButton("Modificar");
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        labelResultado.setText("");
        try {
          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd1","root" ,"");
          Statement comando=conexion.createStatement();
          int cantidad = comando.executeUpdate("update articulos set descripcion='" + tf1.getText() + "'," +
                                           "precio=" + tf2.getText() + " where codigo="+tf3.getText());
          //Método if, si existe el codigo artículo introducido, imprime el mensaje de que lo modifica
          //Si no existe, imprime el segundo mensaje.
          if (cantidad==1) {
            labelResultado.setText("Se modifico la descripcion y el precio del artículo con dicho código");
          } else {
            labelResultado.setText("No existe un artículo con dicho código");
          }
          conexion.close();
        } catch(SQLException ex){
          setTitle(ex.toString());
        }                
      }
    });
    
    //Creamos el botón Consulta por código, conectado a bd1
    btnConsultaPorCdigo = new JButton("Consulta por código");
    btnConsultaPorCdigo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        labelResultado.setText("");
        tf1.setText("");
        tf2.setText("");        
        try {
          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd1","root" ,"");
          Statement comando=conexion.createStatement();
          ResultSet registro = comando.executeQuery("select descripcion,precio from articulos where codigo="+tf3.getText());
          //Método IF donde analiza si el Codigo introducido exite o no, e imprimirá un mensaje o otro.
          if (registro.next()==true) {
            tf1.setText(registro.getString("descripcion"));
            tf2.setText(registro.getString("precio"));
          } else {
            labelResultado.setText("No existe un artículo con dicho código");
          }
          conexion.close();
        } catch(SQLException ex){
          setTitle(ex.toString());
        }
      }
    });
    //le damos posición al boton de Consulta
    btnConsultaPorCdigo.setBounds(20, 250, 180, 23);
    contentPane.add(btnConsultaPorCdigo);
    
    //Creamos la linea de texto y le damos posición en la pantalla
    lblIngreseCdigoDe = new JLabel("Ingrese código de articulo a consultar:");
    lblIngreseCdigoDe.setBounds(100, 300, 243, 14);
    contentPane.add(lblIngreseCdigoDe);
    
    tf3 = new JTextField();
    tf3.setBounds(350, 300, 100, 20);
    contentPane.add(tf3);
    tf3.setColumns(10);
    cargarDriver ();
    
    //damos posicion al btnNewButton_1, y ejecuta después el método cargarDriver(), para que se imprima por pantalla.
    btnNewButton_1.setBounds(21, 190, 179, 23);
    contentPane.add(btnNewButton_1);
    cargarDriver();
  }
  //Carga el drive, necesario para que se imprima por pantalla
  private void cargarDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }catch(Exception ex) {
      setTitle(ex.toString());
    }
  }
}