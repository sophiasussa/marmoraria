package app.views.novousuario;

import app.components.avataritem.AvatarItem;
import app.model.Funcionario;
import app.model.Perfil;
import app.model.TipoEndereco;
import app.model.TipoTelefone;
import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;

@PageTitle("NovoUsuario")
@Route(value = "my-view9", layout = MainLayout.class)
@Uses(Icon.class)
public class NovoUsuarioView extends Composite<VerticalLayout> {

    public NovoUsuarioView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        Tabs tabs = new Tabs();
        AvatarItem avatarItem = new AvatarItem();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        PasswordField passwordField = new PasswordField();
        FormLayout formLayout2Col2 = new FormLayout();
        ComboBox<Perfil> comboBox = new ComboBox();
        ComboBox<Funcionario> comboBox2 = new ComboBox();
        RouterLink routerLink = new RouterLink();
        RouterLink routerLink2 = new RouterLink();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("49px");
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        avatarItem.setWidth("min-content");
        setAvatarItemSampleData(avatarItem);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        textField.setPlaceholder("Nome");
        textField.setWidth("min-content");
        passwordField.setPlaceholder("Senha");
        passwordField.setWidth("min-content");
        formLayout2Col2.setWidth("100%");
        comboBox.setPlaceholder("Perfil");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        comboBox2.setPlaceholder("Funcionario");
        comboBox2.setWidth("min-content");
   //     setComboBoxSampleData(comboBox2);
        routerLink.setText("Custom View");
        routerLink.setRoute(NovoUsuarioView.class);
//        routerLink.setWidth("min-content");
        routerLink2.setText("Custom View");
        routerLink2.setRoute(NovoUsuarioView.class);
   //     routerLink2.setWidth("min-content");
        buttonPrimary.setText("Salvar");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutRow);
        layoutRow.add(tabs);
        layoutRow.add(avatarItem);
        getContent().add(layoutColumn2);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(passwordField);
        layoutColumn2.add(formLayout2Col2);
        formLayout2Col2.add(comboBox);
        formLayout2Col2.add(comboBox2);
        formLayout2Col2.add(routerLink);
        formLayout2Col2.add(routerLink2);
        layoutColumn2.add(buttonPrimary);
    }

/* 
    private void setComboBoxSampleData(ComboBox<Perfil> comboBox) {
        List<Perfil> tiposTelefone = controller.pesquisarTodos();
        comboBox.setItems(tiposTelefone);
        comboBox.setItemLabelGenerator(tipoTelefone -> tipoTelefone.getNome());
    }
    
    private void setComboBoxSampleData1(ComboBox<Funcionario> comboBox2) {
        List<Funcionario> tiposEndereco = controller1.pesquisarTodos();
        comboBox2.setItems(tiposEndereco);
        comboBox2.setItemLabelGenerator(tipoEndereco -> tipoEndereco.getNome());
    }*/

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

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
