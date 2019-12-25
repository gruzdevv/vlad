
package library;

public class Gr {
    
    private int gr_id;
    private String gr_name;
    private int curator_id;
    
    public Gr(int gr_id, String gr_name, int curator_id) {
        this.gr_id = gr_id;
        this.gr_name = gr_name;
        this.curator_id = curator_id;
    }

    public int getGr_id() {
        return gr_id;
    }

    public String getGr_name() {
        return gr_name;
    }

    public int getCurator_id() {
        return curator_id;
    }

}
