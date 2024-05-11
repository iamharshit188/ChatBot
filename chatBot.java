import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ChatBot extends JFrame implements KeyListener {

    JPanel p = new JPanel();
    JTextArea dialog = new JTextArea(20, 50);
    JTextArea input = new JTextArea(1, 50);
    JScrollPane scroll = new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    String[][] chatBot = {
            // Standard greetings
            {"hi", "hello", "hola", "ola", "howdy"},
            {"Hey there!", "Hello!", "Hi!", "Hola!", "Howdy!"},

            // Question greetings
            {"how are you", "how r you", "how r u", "how are u"},
            {"I'm good, thanks!", "Doing well, thank you!", "Pretty good!", "Not too bad!"},

            // Yes
            {"yes", "sure", "yeah"},
            {"No problem!", "Great!", "Awesome!"},

            // Weather
            {"weather", "how's the weather", "what's the weather like"},
            {"The weather is nice today!", "It's sunny outside!", "Looks like it might rain later."},

            // Time
            {"time", "what's the time", "what time is it"},
            {"It's currently " + getTime() + "."},

            // Jokes
            {"tell me a joke", "say something funny", "joke"},
            {"BBD University has one of the best Placement", "I'm reading a book on anti-gravity. It's impossible to put down!", " i Dont know much jokes "},

            // General knowledge
            {"tell me something interesting", "interesting fact", "fun fact"},
            {"Did you know that the shortest war in history was between Britain and Zanzibar on August 27, 1896? It lasted only 38 minutes!", "The Eiffel Tower can be 15 cm taller during the summer due to thermal expansion of the metal.", "The world's largest desert is not the Sahara, but Antarctica!"},

            // Default
            {"shut up", "you're bad", "noob", "stop talking",
                    "I am dumb"}
    };

    public static void main(String[] args) {
        new ChatBot();
    }

    public ChatBot() {
        super("Chat Bot");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialog.setEditable(false);
        input.addKeyListener(this);

        p.add(scroll);
        p.add(input);
        p.setBackground(new Color(240, 240, 240)); // Updated background color
        add(p);

        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(false);
            // Grab the user input
            String quote = input.getText();
            input.setText("");
            addText("-->You:\t" + quote);
            quote = quote.trim();

            // Remove punctuation
            while (quote.endsWith("!") || quote.endsWith(".") || quote.endsWith("?")) {
                quote = quote.substring(0, quote.length() - 1).trim();
            }

            // Check for matches in chatBot
            byte response = 0;
            int j = 0;
            while (response == 0) {
                if (inArray(quote.toLowerCase(), chatBot[j * 2])) {
                    response = 2;
                    int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);
                    addText("\n-->Michael\t" + chatBot[(j * 2) + 1][r]);
                }
                j++;
                if (j * 2 == chatBot.length - 1 && response == 0) {
                    response = 1;
                }
            }

            // Default response
            if (response == 1) {
                int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
                addText("\n-->Michael\t" + chatBot[chatBot.length - 1][r]);
            }
            addText("\n");
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(true);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addText(String str) {
        dialog.setText(dialog.getText() + str);
    }

    public boolean inArray(String in, String[] str) {
        for (String s : str) {
            if (s.equals(in)) {
                return true;
            }
        }
        return false;
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(new Date());
    }
}

