package Skoaffären;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public Main() throws SQLException {
        Data d = new Data();
        Beställ b= new Beställ();
        d.getLogin();
        d.connectToAndQueryDatabase();
        b.inloggning();
        b.väljSkor();
    }
        public static void main (String[]args) throws SQLException {
                    new Main();
                }


            }