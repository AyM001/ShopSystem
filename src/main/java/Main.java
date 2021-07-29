import UI.ManagementUI;
import dao.UserDao;
import json.InputOutput;
import model.UserModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws IOException {



        ManagementUI managementUI = new ManagementUI();
        InputOutput inputOutput = new InputOutput();

        System.out.println("Type HELP to see available options");
         inputOutput.jsonToJAVA();
         managementUI.startManagementUI();


    }
}
