package app.views.perfilusuario;

import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("PerfilUsuario")
@Route(value = "my-view8", layout = MainLayout.class)
@Uses(Icon.class)
public class PerfilUsuarioView extends Composite<VerticalLayout> {

    public PerfilUsuarioView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Avatar avatar = new Avatar();
        TextField textField = new TextField();
        Hr hr = new Hr();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        Hr hr2 = new Hr();
        FormLayout formLayout2Col2 = new FormLayout();
        PasswordField passwordField = new PasswordField();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        avatar.setName("Firstname Lastname");
        textField.setLabel("Text field");
        textField.setWidth("min-content");
        formLayout2Col.setWidth("100%");
        textField2.setLabel("Text field");
        textField2.setWidth("min-content");
        textField3.setLabel("Text field");
        textField3.setWidth("min-content");
        formLayout2Col2.setWidth("100%");
        passwordField.setLabel("Password field");
        passwordField.setWidth("min-content");
        buttonSecondary.setText("Alterar Senha");
        buttonSecondary.setWidth("min-content");
        buttonSecondary.setHeight("50px");
        getContent().add(layoutColumn2);
        layoutColumn2.add(avatar);
        layoutColumn2.add(textField);
        layoutColumn2.add(hr);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField2);
        formLayout2Col.add(textField3);
        layoutColumn2.add(hr2);
        layoutColumn2.add(formLayout2Col2);
        formLayout2Col2.add(passwordField);
        formLayout2Col2.add(buttonSecondary);
    }
}
