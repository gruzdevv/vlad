package library;

/**
 *
 * @author grafinina
 */
public class Kvitok {

    private int kvitok_id;
    private String kvitok_data;
    private String kvitok_time;
    private int summa;
    private int worker_id;
    private int buyer_id;
    
    public Kvitok(int kvitok_id, String kvitok_data, String kvitok_time, int summa, int worker_id, int buyer_id) {
        this.kvitok_id = kvitok_id;
        this.kvitok_data = kvitok_data;
        this.kvitok_time = kvitok_time;
        this.summa = summa;
        this.worker_id = worker_id;
        this.buyer_id = buyer_id;
    }

    public int getKvitok_id() {
        return kvitok_id;
    }

    public String getKvitok_data() {
        return kvitok_data;
    }

    public String getKvitok_time() {
        return kvitok_time;
    }

    public int getSumma() {
        return summa;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }


}