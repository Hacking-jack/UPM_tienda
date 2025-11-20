package es.upm.etsisi.poo.commands.general;

import es.upm.etsisi.poo.commands.Command;

public class CommandHelp implements Command {

    @Override
    public boolean execute() {
        System.out.println("Commands:" +
                "\n client add \"<nombre>\" <DNI> <email> <cashId>\n" +
                "client remove <DNI>\n " +
                "client list\n " +
                "cash add [<id>] \"<nombre>\"<email>\n " +
                "cash remove <id>\n " +
                "cash list\n " +
                "cash tickets <id>\n " +
                "ticket new [<id>] <cashId> <userId>\n " +
                "ticket add <ticketId><cashId> <prodId> <amount> [--p<txt> --p<txt>]\n " +
                "ticket remove <ticketId><cashId> <prodId>\n " +
                "ticket print <ticketId> <cashId>\n " +
                "ticket list\n " +
                "prod add <id> \"<name>\" <category> <price>\n " +
                "prod update <id> NAME|CATEGORY|PRICE <value>\n " +
                "rod addFood [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>\n " +
                "prod addMeeting [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>\n " +
                "prod list\n " +
                "prod remove <id>\n " +
                "help\n " +
                "echo “<text>”\n " +
                "exit\n\n" +


                "Categories: MERCH, STATIONERY, CLOTHES, BOOKS, ELECTRONICS\n" +
                "Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%" +
                ", CLOTHES 7%, BOOKS 10%, ELECTRONICS 3%.\n");
        return true;
    }
}
