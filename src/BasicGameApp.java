import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*; //library for keyboard use
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//Sam Yu
//12/2/21
//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 800;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public SoundFile gears;
    public SoundFile laugh;
    public SoundFile crash;
    public SoundFile fusion;
    public SoundFile bruh;
    public SoundFile epic;
    public SoundFile music;
    public SoundFile coms;
    public SoundFile tank;
    public long startTime;
    public long currentTime;
    public long elapsedTime;
    public long fuelspawnstartTime;
    public long fuelspawncurrentTime;
    public long fuelspawnelapsedTime;
    public boolean startTimer;
    public boolean startfuelstartTimer;
    public int score;
    public int health;
    public int more;

    public BufferStrategy bufferStrategy;
    public Image astroPic;
    public Image Background;
    public Image teslacarpic;
    public Image elonlaughpic;
    public Image elonpointspic;
    public Image elonstarespic;
    public Image elonsuspic;
    public Image medpic;
    public Image lavapic;
    public Image eloncringepic;
    public Image sidewayspic;
    public Image elondrippic;
    public Image purplepic;
    public Image goldpic;


    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private Astronaut astro;
    private Astronaut tesla;
    private teslacar teslacar;
    private Elonlaugh[] elonlaugh;
    private Elonpoints[] elonpoints;
    private purple purpleI;
    private gold goldI;
    private Elonstares elonstares;
    private Elonsus[] elonsus;
    private Med Medkit;
    private Lava[] lava;
    private Eloncringe eloncringe;
    private Sideways sideways;
    private Elondrip elondrip;
    private HardCore hardcore;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {

        setUpGraphics();
        canvas.addKeyListener(this);
        gears = new SoundFile("Explosion 01.wav");
        crash = new SoundFile("Crash.wav");
        bruh = new SoundFile("Gears.wav");
        laugh = new SoundFile("Laugh.wav");
        music = new SoundFile("Blizzard 02.wav");
        epic = new SoundFile("Epic.wav");
        fusion = new SoundFile("Laser Gun.wav");
        coms = new SoundFile("Tune.wav");
        tank = new SoundFile("Soda.wav");
        //variable and objects
        //create (construct) the objects needed for the game and load up
        astroPic = Toolkit.getDefaultToolkit().getImage("Musk.png"); //load the picture
        astro = new Astronaut(10, 100);
        Background = null;
        try{
            URL url = new URL(pull());
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent","Mozilla/5.0");

            conn.connect();
            InputStream urlStream = conn.getInputStream();
            Background = ImageIO.read(urlStream);
        } catch (IOException e){
            System.out.println("Something is worng");
        }
        catch(ParseException e){
            System.out.println("Something is worng");

        }


        //Background = Toolkit.getDefaultToolkit().getImage(); //load the picture
        tesla = new Astronaut(100, 100);

        teslacar = new teslacar(415, 400);
        if (teslacar.isAlive == true) {
            teslacarpic = Toolkit.getDefaultToolkit().getImage("Crim.png");
        }

        elonlaughpic = Toolkit.getDefaultToolkit().getImage("Rock.png"); //load the picture
        elonlaugh = new Elonlaugh[20+more];
        elonpointspic = Toolkit.getDefaultToolkit().getImage("Elonpoints.png"); //load the picture
        elonpoints = new Elonpoints[0];
        elonstarespic = Toolkit.getDefaultToolkit().getImage("gas1.png");
        elonstares = new Elonstares(1000, 0);
        purplepic = Toolkit.getDefaultToolkit().getImage("purple.png");
        purpleI = new purple(1000, 0);
        goldpic = Toolkit.getDefaultToolkit().getImage("Gold.png");
        goldI = new gold(0, 800);
        elonsuspic = Toolkit.getDefaultToolkit().getImage("Fuel.png");
        elonsus = new Elonsus[3];
        medpic = Toolkit.getDefaultToolkit().getImage("medkit.png");
        int randomX1 = (int) (Math.random() * 1000);
        int randomY2 = (int) (Math.random() * 700);
        Medkit = new Med(randomX1, randomY2);
        lavapic = Toolkit.getDefaultToolkit().getImage("Lava.png");
        lava = new Lava[4];
        eloncringepic = Toolkit.getDefaultToolkit().getImage("Fusioncore.png");
        eloncringe = new Eloncringe(450, 350);
        sidewayspic = Toolkit.getDefaultToolkit().getImage("Musk.png");
        sideways = new Sideways(300, 600);
        elondrippic = Toolkit.getDefaultToolkit().getImage("Elondrip.png");
        elondrip = new Elondrip(400, 400);


        for (int i = 0; i < elonlaugh.length; i++) {
            int randomX = (int) (Math.random() * 1000);
            int randomY = (int) (Math.random() * 700);
            elonlaugh[i] = new Elonlaugh(randomX, randomY);


        }
        for (int i = 0; i < elonpoints.length; i++) {
            int randomX = (int) (Math.random() * 900) + 100;
            int randomY = (int) (Math.random() * 600) + 100;
            elonpoints[i] = new Elonpoints(randomX, randomY);
        }
        for (int i = 0; i < elonsus.length; i++) {
            int randomX = (int) (Math.random() * 1000);
            int randomY = (int) (Math.random() * 700);
            elonsus[i] = new Elonsus(randomX, randomY);


        }
        for (int i = 0; i < lava.length; i++) {
            lava[i] = new Lava(450, 400);

            startTime = System.currentTimeMillis();
            fuelspawnstartTime = System.currentTimeMillis();
        }
    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public String pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        String URLofImage = "";
        try {

            URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=RrQGxoFevdORrANDUpFinQAZct7d04w23hdzQJ13");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            System.out.println(URLofImage);


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject JsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println("json"+JsonObject);
        System.out.println(JsonObject.get("url"));
        URLofImage = (String)JsonObject.get("url");

        return URLofImage;
    }
    public void run() {

        music.loop();

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects


            //get the current time
            currentTime = System.currentTimeMillis();
            fuelspawncurrentTime = System.currentTimeMillis();

            //calculate the elapsed time
            if (teslacar.isAlive == true) {
                elapsedTime = (currentTime - startTime) / 1000;

            }

            if (elapsedTime > 30) {
                purpleI.isAlive = true;
            }
            if (elapsedTime > 50) {
                purpleI.isAlive = false;
            }



            for (int i = 0; i < elonpoints.length; i++) {
                if (elapsedTime > 120 && elonlaugh[i].isAlive == true) {
                    elonlaugh[i].dx = elonlaugh[i].dx * 2;
                    elonlaugh[i].dy = elonlaugh[i].dx * 2;
                }
            }

            if (elapsedTime > 75) {
                goldI.isAlive = true;
            }
            if (elapsedTime > 90) {
                goldI.isAlive = false;
            }

            for (int i = 0; i < elonpoints.length; i++) {
                if (teslacar.rec.intersects(elonlaugh[i].rec) && elonlaugh[i].isAlive == true)
                    elonpoints[i].isAlive = false;
            }
            if (elapsedTime > 3) {
                for (int i = 0; i < elonlaugh.length; i++ ) {
                    if (teslacar.rec.intersects(elonlaugh[i].rec)&& teslacar.isAlive == true) {
                        if (elonlaugh[i].CI == false) {
                            health = health + 50;
                            elonlaugh[i].CI = true;
                            if (gears.isPlaying() == false) {
                                gears.play();

                            }

                        }

                    } else
                    {
                        elonlaugh[i].CI = false;
                    }


                    if (health == 200) {
                        teslacar.isAlive = false;
                    }


                }
            }



            if (elapsedTime == 120) {
                health = 0;
            }

            if (health != 0) {
                if (teslacarpic == Toolkit.getDefaultToolkit().getImage("Crim.png")) {
                    teslacarpic = Toolkit.getDefaultToolkit().getImage("Crimdamaged.png");
                }
                if (teslacarpic == Toolkit.getDefaultToolkit().getImage("BetterShip.png")) {
                    teslacarpic = Toolkit.getDefaultToolkit().getImage("BetterShipdamaged.png");
                }
            }
            if(health == 0){
                if (teslacarpic == Toolkit.getDefaultToolkit().getImage("Crimdamaged.png")) {
                    teslacarpic = Toolkit.getDefaultToolkit().getImage("Crim.png");
                }
                if (teslacarpic == Toolkit.getDefaultToolkit().getImage("BetterShipdamaged.png")) {
                    teslacarpic = Toolkit.getDefaultToolkit().getImage("BetterShip.png");
                }
            }


            for (int i = 0; i < elonsus.length; i++) {
                if (teslacar.rec.intersects(elonsus[i].rec) && elonsus[i].isAlive == true) {
                    int randomY2 = (int) (Math.random() * 700);
                    int randomX1 = (int) (Math.random() * 1000);
                    elonsus[i] = new Elonsus(randomX1, randomY2);
                    score = score + 1;
                    if (tank.isPlaying() == false) {
                        tank.play();
                    }
                }
            }
            for (int i = 0; i < lava.length; i++) {
                if (elapsedTime > 120 && lava[i].isAlive == false) {
                    lava[i].isAlive = true;
                }
            }

            for (int i = 0; i < lava.length; i++) {
                if (teslacar.rec.intersects(lava[i].rec) && lava[i].isAlive == true)
                    teslacar.isAlive = false;
            }


            if (teslacar.rec.intersects(sideways.rec) && sideways.isAlive == true) {
                sideways.isAlive = false;
                teslacar.height = teslacar.height + 0;
                teslacar.width = teslacar.width + 0;


            }

            if (teslacar.rec.intersects(eloncringe.rec) && eloncringe.isAlive == true) {
                if (eloncringe.CIF == false) {
                    eloncringe.CIF = true;
                teslacar.dx = teslacar.dx + 1;
                teslacar.dy = teslacar.dy + 1;
                health = 0;
                eloncringe.isAlive = false;
                teslacarpic = Toolkit.getDefaultToolkit().getImage("BetterShip.png");
                if (fusion.isPlaying() == false) {
                    fusion.play();
                }
                }
            } else
        {
            eloncringe.CIF = false;
        }
            if (elapsedTime == 20 || elapsedTime == 30 ||elapsedTime == 50 || elapsedTime == 75 ||elapsedTime == 100 && eloncringe.isAlive == false && teslacar.isAlive == true) {
                eloncringe.isAlive = true;
            }

            if (teslacar.rec.intersects(Medkit.rec) && Medkit.isAlive == true) {
                health = 0;
                Medkit.isAlive = false;
                if (fusion.isPlaying() == false) {
                    fusion.play();
                }
            }
            for (int i = 0; i < elonlaugh.length; i++) {
                if (elonlaugh[i].width != 150) {
                    if (elapsedTime == 10 ||elapsedTime == 20 || elapsedTime%30 == 0 && Medkit.isAlive == false && teslacar.isAlive == true) {
                        Medkit.isAlive = true;
                    }
                }
            }


                for (int i = 0; i < elonlaugh.length; i++) {
                    if (elonlaugh[i].width == 150) {
                        if (elapsedTime %5 == 0 && Medkit.isAlive == false && teslacar.isAlive == true) {
                            Medkit.isAlive = true;
                        }
                    }
                }

            for (int i = 0; i < elonlaugh.length; i++) {
                if (eloncringe.rec.intersects(elonlaugh[i].rec) && elonlaugh[i].isAlive == true)
                    eloncringe.isAlive = false;


            }


            for (int i = 0; i < elonlaugh.length; i++) {
                if (Medkit.rec.intersects(elonlaugh[i].rec) && elonlaugh[i].isAlive == true)
                    Medkit.isAlive = false;

                }

            if(teslacar.dx >= 10 && teslacar.dy >= 10){
                teslacar.dx = 7;
                teslacar.dy = 7;
            }
//fail safe procedure


            for (int i = 15; i < elonlaugh.length; i++) {
                if (elonstares.rec.intersects(elonlaugh[i].rec) && elonlaugh[i].isAlive == true)
                    elonlaugh[i].isAlive = false;

            }
            if (teslacar.rec.intersects(elonstares.rec) && teslacar.isAlive == true) {
                teslacar.isAlive = false;
            }
            if (teslacar.rec.intersects(purpleI.rec) && purpleI.isAlive == true) {
                teslacar.isAlive = false;
            }
            if (teslacar.rec.intersects(goldI.rec) && goldI.isAlive == true) {
                teslacar.isAlive = false;
            }
            if (teslacar.rec.intersects(astro.rec) && astro.isAlive == true) {
                astro.isAlive = false;
                teslacar.height = teslacar.height + 0;
                teslacar.width = teslacar.width + 0;


            }
            if (teslacar.width == 160 && teslacar.height == 95) {
                teslacarpic = Toolkit.getDefaultToolkit().getImage("BFR.png"); //load the picture;
                bruh.play();
                elondrip.isAlive = true;

            }


            if (teslacar.isAlive == false) {
                if (gears.isPlaying() == false) {
                    gears.play();
                }

            }

            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        astro.move();
        if (teslacar.isAlive == true) {
            teslacar.move();
        }
        for (int i = 0; i < elonlaugh.length; i++) {
            elonlaugh[i].move();
        }
        for (int i = 0; i < elonpoints.length; i++) {
            elonpoints[i].move();
        }
        elonstares.move();
        if (purpleI.isAlive == true) {
            purpleI.move();
        }
        if (goldI.isAlive == true) {
            goldI.move();
        }
        for (int i = 0; i < elonsus.length; i++) {
            elonsus[i].move();
        }
        for (int i = 0; i < lava.length; i++) {
            if (lava[i].isAlive == true) {
                lava[i].move();
            }
        }
        eloncringe.move();
        Medkit.move();
        sideways.move();


    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        JPanel panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)


    }


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of the astronaut
        g.drawImage(Background, 0, 0, WIDTH, HEIGHT, null);

        g.setColor(Color.red);
        if (elapsedTime > 0 && elapsedTime < 5 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("*W,A,S,D to move//Avoid stellar objects//Collect fuel tanks//Survive*", 85, 350);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }
        g.setColor(Color.yellow);
        if (teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 10));
            g.drawString("Spacecraft Health", 25, 685);
        }

        g.setColor(Color.red);
        if (elapsedTime > 10 && elapsedTime < 15 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("WE HAVE DETECTED YOUR EMERGENCY SIGNAL, HELP IS ON ITS WAY", 275, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }

        g.setColor(Color.red);
        if (elapsedTime > 25 && elapsedTime < 30 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("STAY STRONG AND STAY SAFE, OUR EMERGENCY FLEET HAS DEPARTED", 200, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }

        g.setColor(Color.red);
        if (elapsedTime > 40 && elapsedTime < 45 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("OUR FLEET HAS PASSED SAGGITARIUS A*, WE WILL SOON ARRIVE", 200, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }

        g.setColor(Color.red);
        if (elapsedTime > 60 && elapsedTime < 65 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("GOOD JOLLY CAPTAIN, WE ARE HALF WAY THERE. YOU WILL BE RESCUED", 175, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }

        g.setColor(Color.red);
        if (elapsedTime > 90 && elapsedTime < 95 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("THE FLEET IS NEARING ANRDOMEDA, HELP YOU ARRIVE SHORTLY", 275, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }
        g.setColor(Color.red);
        if (elapsedTime > 110 && elapsedTime < 112 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 120));
            g.drawString("!", 450, 400);
        }
        g.setColor(Color.red);
        if (elapsedTime > 113 && elapsedTime < 115 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 120));
            g.drawString("!", 450, 400);
        }
        g.setColor(Color.red);
        if (elapsedTime > 116 && elapsedTime < 118 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 120));
            g.drawString("!", 450, 400);
        }

        g.setColor(Color.red);
        if (elapsedTime > 100 && elapsedTime < 103 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("WAIT, HOW ARE YOU STILL ALIVE?", 300, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }
        g.setColor(Color.red);
        if (elapsedTime > 105 && elapsedTime < 110 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("THIS IS NOT POSSIBLE.", 300, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }

        g.setColor(Color.red);
        if (elapsedTime > 115 && elapsedTime < 120 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("DID YOU REALLY THINK WE WILL SAVE YOU?", 275, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }
        g.setColor(Color.red);
        if (elapsedTime > 120 && elapsedTime < 125 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("I AM CERTAINLY SURPRISED YOU SURVIVED THIS LONG", 275, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }
        g.setColor(Color.red);
        if (elapsedTime > 130 && elapsedTime < 132 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("IGNORANT SOUL", 300, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }


        g.setColor(Color.red);
        if (elapsedTime > 133 && elapsedTime < 138 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("THIS IS YOUR FATE NOW", 300, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }
        g.setColor(Color.red);
        if (elapsedTime > 138 && elapsedTime < 149 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("HAHAHAHAHHAHA", 350, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }
        g.setColor(Color.red);
        if (elapsedTime > 150 && teslacar.isAlive == true) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("*ALL COMMUNICATIONS LOST*", 350, 30);
            if (coms.isPlaying() == false) {
                coms.play();
            }
        }

        g.setColor(Color.red);
        if (teslacar.isAlive == false) {
            g.setFont(new Font("Poor Richard", Font.BOLD, 20));
            g.drawString("SPACECRAFT DOWN -- Game Over", teslacar.xpos, teslacar.ypos);

        }

        g.setColor(Color.white);
        if (teslacar.isAlive == false) {
            g.setColor(Color.red);
        }
        g.setFont(new Font("Poor Richard", Font.BOLD, 20));
        g.drawString("Survival Score: " + elapsedTime + "", 20, 20);

        g.setColor(Color.yellow);
        if (teslacar.isAlive == false) {
            g.setColor(Color.red);
        }
        g.setFont(new Font("Poor Richard", Font.BOLD, 10));
        g.drawString("Fuel Tanks Collected: " + score + "", 20, 40);


        if (teslacar.isAlive == false) {
            g.setColor(Color.cyan);
            g.setFont(new Font("Poor Richard", Font.BOLD, 45));
            for (int i = 0; i < elonlaugh.length; i++) {
                if (elonlaugh[i].width != 150) {
                    g.drawString("FINAL SCORE: " + score * elapsedTime + "", 200, 200);
                }
            }
            for (int i = 0; i < elonlaugh.length; i++) {
                if (elonlaugh[i].width == 150) {
                    if (teslacar.isAlive == false) {
                        g.setColor(Color.cyan);
                        g.setFont(new Font("Poor Richard", Font.BOLD, 45));
                        g.drawString("FINAL SCORE: " + score * elapsedTime * 3 + "", 200, 200);
                    }
                }

            }
        }
        

        if (score * elapsedTime > 2000 && score * elapsedTime < 7500 && teslacar.isAlive == false) {
            g.setColor(Color.cyan);
            g.setFont(new Font("Poor Richard", Font.BOLD, 10));
            g.drawString("What a talented player, want a challenge? Try pressing the enter key in the next round!", 175, 225);
        }
        if (score * elapsedTime > 7500 && teslacar.isAlive == false) {
            g.setColor(Color.yellow);
            g.setFont(new Font("Poor Richard", Font.BOLD, 10));
            g.drawString("You deserve my respect with this heavenly score, hats off to you.", 200, 225);
            teslacar.width = 120;
            teslacar.height = 400;
            teslacarpic = Toolkit.getDefaultToolkit().getImage("Winner.png");
        }

        g.setColor(Color.red);
        g.drawRect(10, 700, 200, 25);
        g.setColor(Color.yellow);
        if (teslacar.isAlive == true) {
            g.fillRect(10, 700, 200 - health, 25);


        }


        g.drawImage(teslacarpic, teslacar.xpos, teslacar.ypos, teslacar.width, teslacar.height, null);
        if (teslacar.isAlive == false) {
            teslacarpic = Toolkit.getDefaultToolkit().getImage("Boom.png");
            //load the picture
        }


        for (int i = 0; i < elonsus.length; i++) {
            g.drawImage(elonsuspic, elonsus[i].xpos, elonsus[i].ypos, elonsus[i].width, elonsus[i].height, null);
        }
        for (int i = 0; i < lava.length; i++) {
            if (lava[i].isAlive == true) {
                g.drawImage(lavapic, lava[i].xpos, lava[i].ypos, lava[i].width, lava[i].height, null);
            }
        }
		/*
		if (astro.isAlive == true) {
			g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
		}

		*/
        for (int i = 0; i < elonlaugh.length; i++) {
            g.drawImage(elonlaughpic, elonlaugh[i].xpos, elonlaugh[i].ypos, elonlaugh[i].width, elonlaugh[i].height, null);
        }


        for (int i = 0; i < elonpoints.length; i++) {
            g.drawImage(elonpointspic, elonpoints[i].xpos, elonpoints[i].ypos, elonpoints[i].width, elonpoints[i].height, null);
        }


        if (elonstares.isAlive == true) {
            g.drawImage(elonstarespic, elonstares.xpos, elonstares.ypos, elonstares.width, elonstares.height, null);
        }
        if (purpleI.isAlive == true) {
            g.drawImage(purplepic, purpleI.xpos, purpleI.ypos, purpleI.width, purpleI.height, null);
        }
        if (goldI.isAlive == true) {
            g.drawImage(goldpic, goldI.xpos, goldI.ypos, goldI.width, goldI.height, null);
        }
        if (eloncringe.isAlive == true) {
            g.drawImage(eloncringepic, eloncringe.xpos, eloncringe.ypos, eloncringe.width, eloncringe.height, null);
        }
        if (Medkit.isAlive == true) {
            g.drawImage(medpic, Medkit.xpos, Medkit.ypos, Medkit.width, Medkit.height, null);
        }
        /*

		if (sideways.isAlive == true) {
			g.drawImage(sidewayspic, sideways.xpos, sideways.ypos, sideways.width, sideways.height, null);
		}
		if (elondrip.isAlive == true) {
			g.drawImage(elondrippic, elondrip.xpos, elondrip.ypos, elondrip.width, elondrip.height, null);
		}
*/

        g.dispose();

        bufferStrategy.show();
    }


    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode;
        keyCode = e.getKeyCode();

        if (keyCode == 87) {
            teslacar.up = false;
        }
        if (keyCode == 83) {
            teslacar.down = false;
        }
        if (keyCode == 65) {
            teslacar.left = false;
        }
        if (keyCode == 68) {
            teslacar.right = false;
        }
        if (keyCode == 38) {
            teslacar.up = false;
        }
        if (keyCode == 40) {
            teslacar.down = false;
        }
        if (keyCode == 37) {
            teslacar.left = false;
        }
        if (keyCode == 39) {
            teslacar.right = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode;
        KeyCode = e.getKeyCode();

        char keyCharacter = e.getKeyChar();
        char key = e.getKeyChar();

        if (KeyCode == 87 || KeyCode == 38) {
            teslacar.up = true;
        }
        if (KeyCode == 83 || KeyCode == 40) {
            teslacar.down = true;
        }
        if (KeyCode == 65 || KeyCode == 37) {
            teslacar.left = true;
        }
        if (KeyCode == 68 || KeyCode == 39) {
            teslacar.right = true;
        }

        if (elapsedTime <= 5) {
            if (KeyCode == 10 || KeyCode == 49) {
                for (int i = 0; i < elonlaugh.length; i++) {
                    elonlaugh[i].width = 150;
                    elonlaugh[i].height = 150;
                    Background = Toolkit.getDefaultToolkit().getImage("Burn.jpg"); //load the picture
                    tesla = new Astronaut(100, 100);
                    music.stop();
                    epic.loop();
                    laugh.play();
                }
            }
        }

    }
}





