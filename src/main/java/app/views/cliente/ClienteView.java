package app.views.cliente;

import app.components.avataritem.AvatarItem;
import app.controller.ControllerCidade;
import app.controller.ControllerCliente;
import app.controller.ControllerTipoEndereco;
import app.controller.ControllerTipoTelefone;
import app.data.SamplePerson;
import app.model.Cidade;
import app.model.Cliente;
import app.model.Endereco;
import app.model.Telefone;
import app.model.TipoEndereco;
import app.model.TipoTelefone;
import app.services.SamplePersonService;
import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Cliente")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ClienteView extends Composite<VerticalLayout> {
    Grid<Cliente> minimalistGrid = new Grid(Cliente.class, false);
    ControllerCliente controller = new ControllerCliente(); 
    ControllerTipoTelefone controller1 = new ControllerTipoTelefone();
    ControllerCidade controller2 = new ControllerCidade();
    ControllerTipoEndereco controller3 = new ControllerTipoEndereco();

    public ClienteView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        Tabs tabs = new Tabs();
        AvatarItem avatarItem = new AvatarItem();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        TextField textField = new TextField();
        textField.addClassName("rounded-text-field");  
        Button buttonPrimary = new Button();
        List<Cliente> clientes = controller.listarTodos(); 
        minimalistGrid.setItems(clientes);

        Grid.Column<Cliente> idColumn = minimalistGrid.addColumn(Cliente::getId).setHeader("ID").setKey("id");
            if (idColumn != null) {
                idColumn.setVisible(false);
        }

        minimalistGrid.addColumn(Cliente::getNome).setHeader("Nome");
        minimalistGrid.addColumn(Cliente::getCpf).setHeader("CNPJ/CPF");
        minimalistGrid.addColumn(Cliente::getRg).setHeader("IE/RG");

        minimalistGrid.addComponentColumn(cliente -> {
            Button telefoneButton = new Button(VaadinIcon.PHONE.create());
            telefoneButton.getStyle().set("border-radius", "50%");
            telefoneButton.getStyle().set("background-color", "#9E9E9E");
            telefoneButton.getStyle().set("color", "#FFFFFF");
            telefoneButton.getStyle().set("cursor", "pointer");
            telefoneButton.addClickListener(event -> {
                Dialog dialog = new Dialog();
                dialog.add("Telefones do Cliente:");
                cliente.getTelefones().forEach(telefone -> {
                    dialog.add(telefone.getNumero() + " (" + telefone.getTipoTelefone().getNome() + ")");
                });
                dialog.open();
            });
            return telefoneButton;
        }).setHeader("Telefones");

        minimalistGrid.addComponentColumn(cliente -> {
            Button enderecosButton = new Button(VaadinIcon.HOME.create());
            enderecosButton.getStyle().set("border-radius", "50%");
            enderecosButton.getStyle().set("background-color", "#9E9E9E");
            enderecosButton.getStyle().set("color", "#FFFFFF");
            enderecosButton.getStyle().set("cursor", "pointer");
            enderecosButton.addClickListener(event -> {
                Dialog dialog = new Dialog();
                dialog.add("Endereços do Cliente:");
                cliente.getEnderecos().forEach(endereco -> {
                    dialog.add(endereco.getLogradouro() + ", " + endereco.getNumero() + " - " + endereco.getBairro() + ", " + endereco.getCidade().getNome() + " (" + endereco.getTipoEndereco().getNome() + ")");
                });
                dialog.open();
            });
            return enderecosButton;
        }).setHeader("Endereços");
        
        minimalistGrid.addComponentColumn(cliente -> {
            MenuBar menuBar = new MenuBar();
            MenuItem menuItem = menuBar.addItem("...");
            SubMenu subMenu = menuItem.getSubMenu();
            subMenu.addItem("Editar", event -> abrirDialogoEdicao(cliente));
            subMenu.addItem("Excluir", event -> {
                String nomeCliente = cliente.getNome(); 
                
                int clienteId = controller.encontrarIdClientePorNome(nomeCliente); 
                Notification.show("Excluindo cliente: " + clienteId);
                
                boolean sucesso = controller.excluirCliente(clienteId);
                if (sucesso) {
                    Notification.show("Cliente excluído com sucesso!");
                    minimalistGrid.setItems(controller.listarTodos());
                } else {
                    Notification.show("Erro ao excluir cliente.");
                }
            });
            return menuBar;
        }).setHeader("Opções");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        avatarItem.setWidth("min-content");
        setAvatarItemSampleData(avatarItem);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow2.setAlignItems(Alignment.END);
        layoutRow2.setJustifyContentMode(JustifyContentMode.END);
        layoutRow2.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        textField.setWidth("min-content");
        buttonPrimary.setIcon(VaadinIcon.SEARCH.create());
        buttonPrimary.getStyle().set("border-radius", "50%");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(event -> {
            String searchTerm = textField.getValue().trim();
            
            if (searchTerm.isEmpty()) {
                List<Cliente> todosClientes = controller.listarTodos();
                minimalistGrid.setItems(todosClientes);
            } else if (searchTerm.matches("\\d+")) {
                Cliente resultado = controller.visualizarcpf(searchTerm);
                minimalistGrid.setItems(resultado != null ? Collections.singletonList(resultado) : Collections.emptyList());
            } else {
                Cliente resultado = controller.visualizar(searchTerm);
                minimalistGrid.setItems(resultado != null ? Collections.singletonList(resultado) : Collections.emptyList());
            }
        });
        
        minimalistGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
            GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid.setWidth("100%");
        minimalistGrid.getStyle().set("flex-grow", "0");
        getContent().add(layoutRow);
        layoutRow.add(tabs);
        layoutRow.add(avatarItem);
        getContent().add(layoutColumn2);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(textField);
        layoutRow2.add(buttonPrimary);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn2.add(minimalistGrid);
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }

    private void setAvatarItemSampleData(AvatarItem avatarItem) {
        avatarItem.setHeading("Aria Bailey");
        avatarItem.setDescription("Endocrinologist");
        avatarItem.setAvatar(new Avatar("Aria Bailey"));
    }

    private void abrirDialogoEdicao(Cliente cliente) {
        Dialog dialog = new Dialog();
        VerticalLayout layout = new VerticalLayout();
        dialog.setWidth("1200px");
        dialog.setHeight("1000px");

        TextField nomeField = new TextField("Nome");
        nomeField.setValue(cliente.getNome());
        nomeField.addClassName("rounded-text-field");

        TextField cpfField = new TextField("CPF");
        cpfField.setValue(String.valueOf(cliente.getCpf()));
        cpfField.addClassName("rounded-text-field");

        TextField rgField = new TextField("RG");
        rgField.setValue(String.valueOf(cliente.getRg()));
        rgField.addClassName("rounded-text-field");

        HorizontalLayout camposLayout = new HorizontalLayout(nomeField, cpfField, rgField);
        layout.add(camposLayout);

        List<Telefone> telefonesTemp = new ArrayList<>(cliente.getTelefones());    
        Grid<Telefone> telefoneGrid = new Grid<>(Telefone.class);
        telefoneGrid.getStyle().set("border-radius", "10px");
        telefoneGrid.setItems(telefonesTemp);
        telefoneGrid.removeAllColumns();
        telefoneGrid.addColumn(Telefone::getNumero).setHeader("Número");
        telefoneGrid.addColumn(telefone -> telefone.getTipoTelefone().getNome()).setHeader("Tipo");
        updateGridHeight(telefoneGrid, telefonesTemp.size());

        layout.add(telefoneGrid);

        Button addTelefoneButton = new Button("+");
        addTelefoneButton.getElement().getStyle().set("margin-left", "auto");
        addTelefoneButton.getStyle().set("border-radius", "30px");
        addTelefoneButton.getStyle().set("background-color", "#007bff");
        addTelefoneButton.getStyle().set("color", "#ffffff");
        VerticalLayout telefoneAdicionarLayout = new VerticalLayout();
        ComboBox<TipoTelefone> tipoTelefoneComboBox = new ComboBox<>("Tipo");
        tipoTelefoneComboBox.addClassName("rounded-text-field");
        setComboBoxSampleData(tipoTelefoneComboBox);
        TextField novoTelefoneField = new TextField("Número");
        novoTelefoneField.addClassName("rounded-text-field");        
        Button inserirTelefoneButton = new Button("Inserir");

        inserirTelefoneButton.addClickListener(event -> {
            if (telefonesTemp.size() < 5) {
                Telefone novoTelefone = new Telefone();
                novoTelefone.setNumero(Long.parseLong(novoTelefoneField.getValue()));
                novoTelefone.setTipoTelefone(tipoTelefoneComboBox.getValue());
                telefonesTemp.add(novoTelefone);
    
                telefoneGrid.setItems(telefonesTemp);
                telefoneGrid.getDataProvider().refreshAll();
                updateGridHeight(telefoneGrid, telefonesTemp.size());
                telefoneAdicionarLayout.setVisible(false);
                addTelefoneButton.setVisible(true);
                
                tipoTelefoneComboBox.clear();
                novoTelefoneField.clear();
            } else {
                Notification.show("Você já adicionou 5 telefones.");
                tipoTelefoneComboBox.clear();
                novoTelefoneField.clear();
            }
        });

        telefoneAdicionarLayout.add(new HorizontalLayout(tipoTelefoneComboBox, novoTelefoneField), inserirTelefoneButton);
        telefoneAdicionarLayout.setVisible(false);
        layout.add(telefoneAdicionarLayout);

        addTelefoneButton.addClickListener(event -> {
            addTelefoneButton.setVisible(false);
            telefoneAdicionarLayout.setVisible(true);
        });

        layout.add(addTelefoneButton);

        List<Endereco> enderecosTemp = new ArrayList<>(cliente.getEnderecos());
        Grid<Endereco> enderecoGrid = new Grid<>(Endereco.class);
        enderecoGrid.getStyle().set("border-radius", "15px");
        enderecoGrid.setItems(enderecosTemp);
        enderecoGrid.removeAllColumns();

        enderecoGrid.addColumn(Endereco::getLogradouro).setHeader("Logradouro");
        enderecoGrid.addColumn(Endereco::getNumero).setHeader("Número");
        enderecoGrid.addColumn(Endereco::getBairro).setHeader("Bairro");
        enderecoGrid.addColumn(endereco -> endereco.getCidade().getNome()).setHeader("Cidade");
        enderecoGrid.addColumn(endereco -> endereco.getTipoEndereco().getNome()).setHeader("Tipo");
        updateGridHeight(enderecoGrid, enderecosTemp.size());

        layout.add(enderecoGrid);

        Button addEnderecoButton = new Button("+");
        addEnderecoButton.getElement().getStyle().set("margin-left", "auto");
        addEnderecoButton.getStyle().set("border-radius", "30px");
        addEnderecoButton.getStyle().set("background-color", "#007bff");
        addEnderecoButton.getStyle().set("color", "#ffffff");
        VerticalLayout enderecoAdicionarLayout = new VerticalLayout();
        ComboBox<TipoEndereco> tipoEnderecoComboBox = new ComboBox<>("Tipo de Endereço");
        tipoEnderecoComboBox.addClassName("rounded-text-field");  
        setComboBoxSampleData1(tipoEnderecoComboBox);
        ComboBox<Cidade> cidadeComboBox = new ComboBox<>("Cidade");
        cidadeComboBox.addClassName("rounded-text-field");  
        setComboBoxSampleData2(cidadeComboBox);

        TextField logradouroField = new TextField("Logradouro");
        logradouroField.addClassName("rounded-text-field");  
        TextField numeroField = new TextField("Número");
        numeroField.addClassName("rounded-text-field");  
        TextField bairroField = new TextField("Bairro");
        bairroField.addClassName("rounded-text-field");  
        Button inserirEnderecoButton = new Button("Inserir");

        inserirEnderecoButton.addClickListener(event -> {
            if (enderecosTemp.size() < 5) {
                Endereco novoEndereco = new Endereco();
                novoEndereco.setLogradouro(logradouroField.getValue());
                novoEndereco.setNumero(Integer.parseInt(numeroField.getValue()));
                novoEndereco.setBairro(bairroField.getValue());
                novoEndereco.setCidade(cidadeComboBox.getValue());
                novoEndereco.setTipoEndereco(tipoEnderecoComboBox.getValue());
                enderecosTemp.add(novoEndereco);
    
                enderecoGrid.setItems(enderecosTemp);
                enderecoGrid.getDataProvider().refreshAll();
                updateGridHeight(enderecoGrid, enderecosTemp.size());
                enderecoAdicionarLayout.setVisible(false);
                addEnderecoButton.setVisible(true);
                
                tipoEnderecoComboBox.clear();
                cidadeComboBox.clear();
                logradouroField.clear();
                numeroField.clear();
                bairroField.clear();
            } else {
                Notification.show("Você já adicionou 5 endereços.");
                tipoEnderecoComboBox.clear();
                cidadeComboBox.clear();
                logradouroField.clear();
                numeroField.clear();
                bairroField.clear();
            }
        });

        enderecoAdicionarLayout.add(new HorizontalLayout(tipoEnderecoComboBox, cidadeComboBox),
                                    new HorizontalLayout(logradouroField, numeroField, bairroField),
                                    inserirEnderecoButton);
        enderecoAdicionarLayout.setVisible(false);
        layout.add(enderecoAdicionarLayout);

        addEnderecoButton.addClickListener(event -> {
            addEnderecoButton.setVisible(false);
            enderecoAdicionarLayout.setVisible(true);
        });

        layout.add(addEnderecoButton);


        Button salvarButton = new Button("Salvar", event -> {
            cliente.setNome(nomeField.getValue());
            cliente.setCpf(Long.parseLong(cpfField.getValue()));
            cliente.setRg(Long.parseLong(rgField.getValue()));
            cliente.setTelefones(telefonesTemp);
            cliente.setEnderecos(enderecosTemp);
            System.out.println(enderecosTemp);
            boolean sucesso = controller.atualizarCliente(cliente);
            if (sucesso) {
                Notification.show("Cliente atualizado com sucesso!");
                minimalistGrid.setItems(controller.listarTodos());
                dialog.close();
            } else {
                Notification.show("Erro ao atualizar cliente.");
            }
        });
        salvarButton.getStyle().set("border-radius", "10px");
        salvarButton.getStyle().set("background-color", "#28a745");
        salvarButton.getStyle().set("color", "#ffffff");

        Button cancelarButton = new Button(VaadinIcon.CLOSE.create(), event -> dialog.close());
        cancelarButton.getStyle().set("border-radius", "50%");
        cancelarButton.getStyle().set("background-color", "#dc3545");
        cancelarButton.getStyle().set("color", "#ffffff");

        layout.add(new HorizontalLayout(salvarButton, cancelarButton));
        dialog.add(layout);
        dialog.open();
    }

    private void updateGridHeight(Grid<?> grid, int rows) {
        int rowHeight = 40;
        int headerHeight = 56;
        grid.setHeight((rows * rowHeight + headerHeight) + "px");
    }

    private void setComboBoxSampleData(ComboBox<TipoTelefone> tipoTelefoneComboBox) {
        List<TipoTelefone> tiposTelefone = controller1.pesquisarTodos();
        tipoTelefoneComboBox.setItems(tiposTelefone);
        tipoTelefoneComboBox.setItemLabelGenerator(TipoTelefone::getNome);
    }

    private void setComboBoxSampleData1(ComboBox<TipoEndereco> tipoEnderecoComboBox) {
        List<TipoEndereco> tiposEndereco = controller3.pesquisarTodos();
        tipoEnderecoComboBox.setItems(tiposEndereco);
        tipoEnderecoComboBox.setItemLabelGenerator(TipoEndereco::getNome);
    }

    private void setComboBoxSampleData2(ComboBox<Cidade> cidadeComboBox) {
        List<Cidade> cidades = controller2.pesquisarTodos();
        cidadeComboBox.setItems(cidades);
        cidadeComboBox.setItemLabelGenerator(Cidade::getNome);
    }

}
