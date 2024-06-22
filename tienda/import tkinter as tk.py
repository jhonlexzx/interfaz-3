import tkinter as tk
from tkinter import ttk

class RegistroTienda(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Registro de Tienda")
        self.geometry("500x300")

        self.style = ttk.Style()
        self.style.configure('TNotebook.Tab', font=('Helvetica', '12', 'bold'))
        self.style.configure('TLabel', font=('Helvetica', '10'))
        self.style.configure('TButton', font=('Helvetica', '10', 'bold'))

        self.productos = []
        self.clientes = []
        self.ordenes = []
        self.items_orden = []
        self.categorias = []

        self.tab_control = ttk.Notebook(self)
        self.tabs = {}
        tab_names = ['Productos', 'Clientes', 'Ordenes', 'ItemsOrden', 'Categorias']

        for tab_name in tab_names:
            tab = ttk.Frame(self.tab_control)
            self.tab_control.add(tab, text=tab_name)
            self.tabs[tab_name] = tab
        
        self.tab_control.pack(expand=1, fill="both")

        self.init_tabs()

    def init_tabs(self):
        for tab_name in self.tabs:
            init_method = getattr(self, f'init_tab_{tab_name.lower()}', None)
            if init_method:
                init_method(self.tabs[tab_name])

    def create_entry_frame(self, parent, fields, button_text, command):
        frame = ttk.LabelFrame(parent, text=f"Registrar {parent._name}", style="TLabelframe")
        frame.configure(borderwidth=2, relief="ridge")
        entries = {}
        for field in fields:
            ttk.Label(frame, text=field).grid(row=fields.index(field), column=0, padx=5, pady=5, sticky="w")
            entry = ttk.Entry(frame)
            entry.grid(row=fields.index(field), column=1, padx=5, pady=5, sticky="ew")
            entries[field] = entry
        
        ttk.Button(frame, text=button_text, command=lambda: command(entries)).grid(row=len(fields), column=0, columnspan=2, padx=5, pady=10)
        frame.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)
        return frame, entries

    def create_listbox(self, parent):
        listbox_frame = ttk.Frame(parent)
        listbox = tk.Listbox(listbox_frame, height=10, borderwidth=2, relief="ridge")
        listbox.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)
        listbox_frame.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)
        return listbox

    def init_tab_productos(self, tab):
        fields = ["Nombre", "Precio", "Categoría"]
        self.producto_entries, self.producto_entries_values = self.create_entry_frame(tab, fields, "Registrar Producto", self.registrar_producto)
        self.lista_productos = self.create_listbox(tab)

    def init_tab_clientes(self, tab):
        fields = ["Nombre", "Apellido", "ID Cliente"]
        self.cliente_entries, self.cliente_entries_values = self.create_entry_frame(tab, fields, "Registrar Cliente", self.registrar_cliente)
        self.lista_clientes = self.create_listbox(tab)

    def init_tab_ordenes(self, tab):
        fields = ["Cliente", "Items", "Total"]
        self.orden_entries, self.orden_entries_values = self.create_entry_frame(tab, fields, "Registrar Orden", self.registrar_orden)
        self.lista_ordenes = self.create_listbox(tab)

    def init_tab_itemsorden(self, tab):
        fields = ["Producto", "Cantidad"]
        self.item_orden_entries, self.item_orden_entries_values = self.create_entry_frame(tab, fields, "Registrar Item de Orden", self.registrar_item_orden)
        self.lista_items_orden = self.create_listbox(tab)

    def init_tab_categorias(self, tab):
        fields = ["Nombre"]
        self.categoria_entries, self.categoria_entries_values = self.create_entry_frame(tab, fields, "Registrar Categoría", self.registrar_categoria)
        self.lista_categorias = self.create_listbox(tab)

    def registrar_producto(self, entries):
        nombre = entries["Nombre"].get()
        precio = entries["Precio"].get()
        categoria = entries["Categoría"].get()
        if nombre and precio and categoria:
            self.productos.append((nombre, precio, categoria))
            self.lista_productos.insert(tk.END, nombre)
            self.clear_entries(entries)

    def registrar_cliente(self, entries):
        nombre = entries["Nombre"].get()
        apellido = entries["Apellido"].get()
        id_cliente = entries["ID Cliente"].get()
        if nombre and apellido and id_cliente:
            self.clientes.append((nombre, apellido, id_cliente))
            self.lista_clientes.insert(tk.END, f"{nombre} {apellido}")
            self.clear_entries(entries)

    def registrar_orden(self, entries):
        cliente = entries["Cliente"].get()
        items = entries["Items"].get()
        total = entries["Total"].get()
        if cliente and items and total:
            self.ordenes.append((cliente, items, total))
            self.lista_ordenes.insert(tk.END, f"{cliente} - {total}")
            self.clear_entries(entries)

    def registrar_item_orden(self, entries):
        producto = entries["Producto"].get()
        cantidad = entries["Cantidad"].get()
        if producto and cantidad:
            self.items_orden.append((producto, cantidad))
            self.lista_items_orden.insert(tk.END, f"{producto} - {cantidad}")
            self.clear_entries(entries)

    def registrar_categoria(self, entries):
        nombre = entries["Nombre"].get()
        if nombre:
            self.categorias.append(nombre)
            self.lista_categorias.insert(tk.END, nombre)
            self.clear_entries(entries)

    def clear_entries(self, entries):
        for entry in entries.values():
            entry.delete(0, tk.END)

if __name__ == "__main__":
    app = RegistroTienda()
    app.mainloop()
