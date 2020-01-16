package library;

public class Animal {

    private int animal_id;
    private String animal_nick;
    private String animal_name;
    private String animal_birthday;
    private int type_id;
    private int host_id;

    public Animal(int animal_id, String animal_nick, String animal_name, String animal_birthday, int type_id, int host_id) {
        this.animal_id = animal_id;
        this.animal_nick = animal_nick;
        this.animal_name = animal_name;
        this.animal_birthday = animal_birthday;
        this.type_id = type_id;
        this.host_id = host_id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public String getAnimal_nick() {
        return animal_nick;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public String getAnimal_birthday() {
        return animal_birthday;
    }

    public int getType_id() {
        return type_id;
    }

    public int getHost_id() {
        return host_id;
    }


}
