package oop;



import javax.swing.text.html.HTMLDocument;
import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    //public Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    public LinkedList<Animal> animals = new LinkedList();
    public LinkedList<Animal> babies = new
            LinkedList();
    public Vector2d min;
    public Vector2d max;
    int control=0;
    MapBoundary mapBoundary = new MapBoundary();
    public Animal nowe_zwierze;
    public int numberOfDeadAnimals;
    public int ageOfDeadAnimals;
    public int numberOfGrassesEaten;
    public String side="left";
    public int getNumberOfDeadAnimals() {
        return this.numberOfDeadAnimals;
    }

    public int getAgeOfDeadAnimals() {
        return this.ageOfDeadAnimals;
    }

    public AbstractWorldMap() {

        animals = new LinkedList<>();
        babies = new LinkedList<>();
        this.numberOfDeadAnimals = 0;
        this.ageOfDeadAnimals = 0;
        this.numberOfGrassesEaten = 0;
    }

    public Vector2d getMin() {
        return this.min;
    }

    public Vector2d getMax() {
        return this.max;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (Animal i : animals)
        // w animalach szukam starej pozycji i jak znajdę to wymieniam na nowa
        {
            if (i.getPosition().equals(oldPosition)) {
                i.changePosition(newPosition);
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position, Vector2d oldPosition) {

            int x = position.getX();
            int y = position.getY();
            if (x > max.getX() || x < min.getX() || y > max.getY() || y < min.getY()) {
                return false;
            }
            return true;

    }


    public float food(Vector2d position, IWorldMap map) {
        LinkedList<Grass> grasses = new LinkedList();
        float food = 0;
        Grass grass = null;
        grasses = map.getGrasses();
        for (Grass g : grasses) {
            if (g.getPosition().equals(position)) {
                grass = g;
                food = 13;
            }

        }
        if (grass != null) {
            map.removeFromGrasses(grass);
            this.numberOfGrassesEaten+=1;
        }
        return food;
    }

    @Override
    public boolean place(Animal newAnimal) //throws IllegalArgumentException
    {
        Vector2d v = newAnimal.getPosition();

        animals.add(newAnimal);
        mapBoundary.addAnimal(newAnimal);
        return true;
    }

    public void dodajNoweRosliny(IWorldMap map) {
        map.addGrasses(this.numberOfGrassesEaten);
        this.numberOfGrassesEaten = 0;
    }

    @Override
    public void run(MoveDirection[] directions, IWorldMap map,boolean magic,String side) {
        this.side=side;
        MoveDirection[] moves = directions;
        int i = 0;
        for (Animal a : animals) {
            Vector2d v = a.move(moves[i],side);
            float energy1 = a.getEnergy();
            a.ChangeEnergy(food(v, map));
            float energy2 = a.getEnergy();
            if ((energy2 - energy1) == 2) {
                this.numberOfGrassesEaten += 1;
            }
            i++;
        }
        makeBabies();
        for (Animal a : babies) {
            animals.add(a);
            for (Animal b:animals){
                if(a.genesToString().equals(b.genesToString())){
                    b.ile_zwierzat_ma_geny_co_ja=b.ile_zwierzat_ma_geny_co_ja+=1;
                }
            }
            //  System.out.println(".");
        }
        babies.clear();

        usunMartweZwierzeta();
        if(animals.size()<=5 && magic && control<3)
        {
            ((GrassField) map).leftFive();
            control+=1;
        }
        dodajNoweRosliny(map);

    }

    @Override
    public Vector2d wylosujVector() {
        int width = this.max.getX();
        int length = this.max.getY();
        Random generator = new Random();
        int x = generator.nextInt(width);
        int y = generator.nextInt(length);
        int a = generator.nextInt(2);
        int b = generator.nextInt(2);
        if (a == 1)
            x = -x;
        if (b == 1)
            y = -y;
        Vector2d v = new Vector2d(x, y);
        return v;

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal i : animals) {
            if (i.getPosition().equals(position)) {
                return true;
            }
        }
        //return animals.containsKey(position);
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        //return animals.get(position);
        for (Animal i : animals) {
            if (i.getPosition().equals(position)) {
                return i;
            }
        }
        return null;
    }

    public MoveDirection[] skretIPrzemieszczenie() {
        int[] losowanie = new int[animals.size()];
        String[] losowanie2 = new String[animals.size()];
        int i = 0;
        for (Animal a : animals) {
            int x = wylosujZgodnieZGenotypem(a);
            losowanie[i] = x;
            losowanie2[i] = x + "";
            i++;
            a.ChangeEnergy((float) -0.25);
            a.updateAge();
        }
        OptionsParser parser = new OptionsParser();
        MoveDirection[] moves = parser.parse(losowanie2);
        return moves;

    }

    public int wylosujZgodnieZGenotypem(Animal a) {
        int[] geny = a.getGenes();
        // losujemy indeks od 0-32
        Random generetor = new Random();
        int w = generetor.nextInt(32);
        return geny[w];
    }

    public void usunMartweZwierzeta() {
        /// wpadla na taki pomysl ze po prostu przepisuje listę
        /// te zwierzeta sie za szybko noza
        LinkedList<Animal> przepisane = new LinkedList();
        for (Animal a : animals) {
            if (a.getEnergy() <= 0) {
                numberOfDeadAnimals += 1;
                ageOfDeadAnimals += a.getAge();
            }
            if (a.getEnergy() > 0) {
                przepisane.add(a);
            }
        }
        animals = przepisane;
    }

    public void makeBabies() {
        //System.out.print("wykonuje sie make babies");
        // LinkedList<Animal> wykreslone=new LinkedList();
        for (int i1 = 0; i1 <= animals.size(); i1++) {
            for (int j1 = i1 + 1; j1 < animals.size(); j1++) {
                // jezeli  ie ma tego w wykreslone to moze sie wykonac
                // nie moze sie wykona jezeli jest wykreslone

                if (animals.get(i1).getPosition().equals(animals.get(j1).getPosition()) && i1 != j1) {
                    //System.out.print("a ten if ile razy");

                    makeABaby(animals.get(i1), animals.get(j1));
                    //wykonaja sie podwojne dzieci

                }
            }
        }
    }

    public int[] wylosujGenotyp(int[] genes) {
        for (int i = 0; i < genes.length; i++) {
            Random generator = new Random();
            genes[i] = generator.nextInt(8);
        }
        boolean[] CzyWszystkie = {false, false, false, false, false, false, false, false};
        Arrays.sort(genes);
        for (int i = 0; i < genes.length; i++) {
            CzyWszystkie[genes[i]] = true;
        }
        int j = 0;
        for (int i = 0; i <= 7; i++) {
            if (CzyWszystkie[i] == false) {
                while (j <= 30 && genes[j] != genes[j + 1])
                    j++;
                genes[j] = i;
            }
        }
        //for(int i=0;i<genes.length;i++)
        //{
        //  System.out.print(genes[i]);
        //}
        return genes;
    }

    public void makeABaby(Animal animal1, Animal animal2) {
        animal1.updateNumberOfChildren();
        animal2.updateNumberOfChildren();
        float energy1 = animal1.getEnergy();
        float energy2 = animal2.getEnergy();
        if (energy1 >= 0.5 && energy2 >= 0.5) {
            float diff = (float) (-0.25);
            animal1.ChangeEnergy(diff);
            animal2.ChangeEnergy(diff);
            Random generator = new Random();
            int x = 1 + generator.nextInt(31);
            int y = 1 + generator.nextInt(31);
            while (x == y && x + 1 != y && y + 1 != x) {
                y = 1 + generator.nextInt(31);
            }
            int max = Math.max(x, y);
            int min = Math.max(x, y);
            int[] genes1 = new int[32];
            int[] genes2 = new int[32];
            int[] babyGenes = new int[32];
            boolean[] CzyWszystkie = {false, false, false, false, false, false, false, false};
            for (int i = 0; i < 32; i++) {
                if (i <= x || i >= y) {
                    babyGenes[i] = genes1[i];

                } else {
                    babyGenes[i] = genes2[i];
                }
                CzyWszystkie[babyGenes[i]] = true;
            }
            for (int i = 0, j = 0; i < 8 && j < 31; i++) {
                if (CzyWszystkie[i] == false) {
                    while (babyGenes[j] != babyGenes[j + 1]) {
                        j++;
                    }
                    babyGenes[j] = i;
                }

            }
            ////*********** trzeba znalezc wolna pozycje dla zwierzecia
            IWorldMap map = animal1.getMap();
            Arrays.sort(babyGenes);
            int ctr = 1;
            int x1 = 0;
            int y1 = 0;
            while (ctr == 1) {
                x1 = generator.nextInt(this.max.getX());
                y1 = generator.nextInt(this.max.getY());
                int ctr1 = generator.nextInt(2);
                int ctr2 = generator.nextInt(2);
                if (ctr1 == 0) {
                    x1 = -x1;
                }
                if (ctr2 == 0) {
                    y1 = -y1;
                }
                ctr = 0;
                for (Animal i : animals) {
                    Animal a = i;
                    if (a.getPosition().equals(new Vector2d(x1, y1))) {
                        ctr = 1;
                    }
                }
                if (this.isOccupied(new Vector2d(x1, y1))) {
                    ctr = 1;
                }
            }

            Animal baby = new Animal(this, new Vector2d(x1, y1), (float) 0.5, babyGenes);
            //map.place(baby);
            babies.add(baby);

        }

        nowe_zwierze = null;
    }


}