import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class TiendaApp extends JFrame {
    private Tienda tienda;
    private Orden ordenActual;

    private JTabbedPane tabbedPane;
    private JPanel panelRegistroCategoria;
    private JPanel panelRegistroProducto;
    private JPanel panelRegistroCliente;
    private JPanel panelGestionOrden;
    private JPanel panelTotalCompras;

    private JComboBox<String> comboCategorias;
    private JComboBox<String> comboProductos;
    private JComboBox<String> comboClientes;
    private JComboBox<String> comboClientesTotal;

    private JTextField entryNombreCategoria;
    private JTextField entryNombreProducto;
    private JTextField entryPrecioProducto;
    private JTextField entryNombreCliente;
    private JTextField entryApellidoCliente;
    private JTextField entryCantidadProducto;

    private JLabel labelTotalOrden;
    private JLabel labelTotalCompras;

    public TiendaApp() {
        setTitle("Tienda App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tienda = new Tienda();
        ordenActual = null;

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 12));
        panelRegistroCategoria = createRegistroCategoriaPanel();
        panelRegistroProducto = createRegistroProductoPanel();
        panelRegistroCliente = createRegistroClientePanel();
        panelGestionOrden = createGestionOrdenPanel();
        panelTotalCompras = createTotalComprasPanel();

        tabbedPane.addTab("Registrar Categoría", panelRegistroCategoria);
        tabbedPane.addTab("Registrar Producto", panelRegistroProducto);
        tabbedPane.addTab("Registrar Cliente", panelRegistroCliente);
        tabbedPane.addTab("Gestionar Orden", panelGestionOrden);
        tabbedPane.addTab("Calcular Total Compras", panelTotalCompras);

        add(tabbedPane);
        inicializarInterfaz();
    }

    private JPanel createRegistroCategoriaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelNombreCategoria = new JLabel("Nombre de la Categoría:");
        entryNombreCategoria = new JTextField(20);
        JButton btnRegistrarCategoria = new JButton("Registrar Categoría");

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(labelNombreCategoria, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(entryNombreCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(btnRegistrarCategoria, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        btnRegistrarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCategoria();
            }
        });

        return panel;
    }

    private JPanel createRegistroProductoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelNombreProducto = new JLabel("Nombre:");
        entryNombreProducto = new JTextField(20);

        JLabel labelPrecioProducto = new JLabel("Precio:");
        entryPrecioProducto = new JTextField(20);

        JLabel labelCategoria = new JLabel("Categoría:");
        comboCategorias = new JComboBox<>();
        comboCategorias.setPreferredSize(new Dimension(200, 25));

        JButton btnRegistrarProducto = new JButton("Registrar Producto");

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(labelNombreProducto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(entryNombreProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelPrecioProducto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(entryPrecioProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(labelCategoria, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(comboCategorias, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(btnRegistrarProducto, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        btnRegistrarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarProducto();
            }
        });

        return panel;
    }

    private JPanel createRegistroClientePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelNombreCliente = new JLabel("Nombre:");
        entryNombreCliente = new JTextField(20);

        JLabel labelApellidoCliente = new JLabel("Apellido:");
        entryApellidoCliente = new JTextField(20);

        JButton btnRegistrarCliente = new JButton("Registrar Cliente");

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(labelNombreCliente, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(entryNombreCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelApellidoCliente, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(entryApellidoCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(btnRegistrarCliente, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        btnRegistrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });

        return panel;
    }

    private JPanel createGestionOrdenPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelSeleccionarCliente = new JLabel("Seleccionar Cliente:");
        comboClientes = new JComboBox<>();
        comboClientes.setPreferredSize(new Dimension(200, 25));

        JButton btnCrearOrden = new JButton("Crear Orden");

        JLabel labelSeleccionarProducto = new JLabel("Seleccionar Producto:");
        comboProductos = new JComboBox<>();
        comboProductos.setPreferredSize(new Dimension(200, 25));

        JLabel labelCantidadProducto = new JLabel("Cantidad:");
        entryCantidadProducto = new JTextField(20);

        JButton btnAgregarProducto = new JButton("Agregar Producto");

        JLabel labelTotalOrdenText = new JLabel("Total Orden:");
        labelTotalOrden = new JLabel("$0.00");

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(labelSeleccionarCliente, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(comboClientes, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(btnCrearOrden, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelSeleccionarProducto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(comboProductos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(labelCantidadProducto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(entryCantidadProducto, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(btnAgregarProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(labelTotalOrdenText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(labelTotalOrden, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        btnCrearOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearOrden();
            }
        });

        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductoAOrden();
            }
        });

        return panel;
    }

    private JPanel createTotalComprasPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelSeleccionarClienteTotal = new JLabel("Seleccionar Cliente:");
        comboClientesTotal = new JComboBox<>();
        comboClientesTotal.setPreferredSize(new Dimension(200, 25));

        JButton btnCalcularTotal = new JButton("Calcular Total");

        JLabel labelTotalComprasText = new JLabel("Total Compras:");
        labelTotalCompras = new JLabel("$0.00");

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(labelSeleccionarClienteTotal, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(comboClientesTotal, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(btnCalcularTotal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelTotalComprasText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(labelTotalCompras, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        btnCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotalCompras();
            }
        });

        return panel;
    }

    private void inicializarInterfaz() {
        // Inicializar combo boxes con datos iniciales
        actualizarComboCategorias();
        actualizarComboClientes();
        actualizarComboProductos();
    }

    private void registrarCategoria() {
        String nombreCategoria = entryNombreCategoria.getText();
        if (!nombreCategoria.isEmpty()) {
            Categoria categoria = new Categoria(nombreCategoria);
            tienda.agregarCategoria(categoria);
            actualizarComboCategorias();
            entryNombreCategoria.setText("");
            JOptionPane.showMessageDialog(this, "Categoría registrada con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de categoría.");
        }
    }

    private void registrarProducto() {
        String nombreProducto = entryNombreProducto.getText();
        String precioProducto = entryPrecioProducto.getText();
        Categoria categoriaSeleccionada = (Categoria) comboCategorias.getSelectedItem();

        if (!nombreProducto.isEmpty() && !precioProducto.isEmpty() && categoriaSeleccionada != null) {
            try {
                double precio = Double.parseDouble(precioProducto);
                Producto producto = new Producto(nombreProducto, precio, categoriaSeleccionada);
                tienda.agregarProducto(producto);
                actualizarComboProductos();
                entryNombreProducto.setText("");
                entryPrecioProducto.setText("");
                JOptionPane.showMessageDialog(this, "Producto registrado con éxito.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un precio válido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }

    private void registrarCliente() {
        String nombreCliente = entryNombreCliente.getText();
        String apellidoCliente = entryApellidoCliente.getText();
        if (!nombreCliente.isEmpty() && !apellidoCliente.isEmpty()) {
            Cliente cliente = new Cliente(nombreCliente, apellidoCliente);
            tienda.agregarCliente(cliente);
            actualizarComboClientes();
            entryNombreCliente.setText("");
            entryApellidoCliente.setText("");
            JOptionPane.showMessageDialog(this, "Cliente registrado con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }

    private void crearOrden() {
        Cliente clienteSeleccionado = (Cliente) comboClientes.getSelectedItem();
        if (clienteSeleccionado != null) {
            ordenActual = new Orden(clienteSeleccionado);
            labelTotalOrden.setText("$0.00");
            JOptionPane.showMessageDialog(this, "Orden creada para el cliente: " + clienteSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente.");
        }
    }

    private void agregarProductoAOrden() {
        if (ordenActual == null) {
            JOptionPane.showMessageDialog(this, "Primero debe crear una orden.");
            return;
        }

        Producto productoSeleccionado = (Producto) comboProductos.getSelectedItem();
        String cantidadStr = entryCantidadProducto.getText();

        if (productoSeleccionado != null && !cantidadStr.isEmpty()) {
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                ordenActual.agregarProducto(productoSeleccionado, cantidad);
                labelTotalOrden.setText(String.format("$%.2f", ordenActual.calcularTotal()));
                entryCantidadProducto.setText("");
                JOptionPane.showMessageDialog(this, "Producto agregado a la orden.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad válida.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto e ingrese la cantidad.");
        }
    }

    private void calcularTotalCompras() {
        Cliente clienteSeleccionado = (Cliente) comboClientesTotal.getSelectedItem();
        if (clienteSeleccionado != null) {
            double totalCompras = tienda.calcularTotalComprasCliente(clienteSeleccionado);
            labelTotalCompras.setText(String.format("$%.2f", totalCompras));
            JOptionPane.showMessageDialog(this, "Total compras del cliente: " + clienteSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente.");
        }
    }

    private void actualizarComboCategorias() {
        comboCategorias.removeAllItems();
        for (Categoria categoria : tienda.getCategorias()) {
            comboCategorias.addItem(categoria.getNombre());
        }
    }

    private void actualizarComboProductos() {
        comboProductos.removeAllItems();
        for (Producto producto : tienda.getProductos()) {
            comboProductos.addItem(producto.getNombre());
        }
    }

    private void actualizarComboClientes() {
        comboClientes.removeAllItems();
        comboClientesTotal.removeAllItems();
        for (Cliente cliente : tienda.getClientes()) {
            comboClientes.addItem(cliente.getNombreCompleto());
            comboClientesTotal.addItem(cliente.getNombreCompleto());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TiendaApp().setVisible(true);
            }
        });
    }
}

class Tienda {
    private ArrayList<Categoria> categorias;
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;

    public Tienda() {
        categorias = new ArrayList<>();
        productos = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public double calcularTotalComprasCliente(Cliente cliente) {
        double total = 0;
        for (Orden orden : cliente.getOrdenes()) {
            total += orden.calcularTotal();
        }
        return total;
    }
}

class Categoria {
    private String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

class Producto {
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Producto(String nombre, double precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}

class Cliente {
    private String nombre;
    private String apellido;
    private ArrayList<Orden> ordenes;

    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        ordenes = new ArrayList<>();
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public void agregarOrden(Orden orden) {
        ordenes.add(orden);
    }

    public ArrayList<Orden> getOrdenes() {
        return ordenes;
    }
}

class Orden {
    private Cliente cliente;
    private ArrayList<ItemOrden> items;

    public Orden(Cliente cliente) {
        this.cliente = cliente;
        items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemOrden(producto, cantidad));
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemOrden item : items) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }
}

class ItemOrden {
    private Producto producto;
    private int cantidad;

    public ItemOrden(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
