package ControlersJFX;

import com.mysql.cj.conf.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Userdata	{
        private IntegerProperty p_id;
        private StringProperty Name;


        private UserData(Integer p_id, String Name) {
            this.p_id = new SimpleIntegerProperty (p_id);
            this.Name = new SimpleStringProperty(Name);

        }

        public IntegerProperty p_idProperty() {
            return p_id;
        }

        public StringProperty NameProperty() {
            return Name;
        }
}
}
