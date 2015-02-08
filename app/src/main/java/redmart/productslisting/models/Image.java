package redmart.productslisting.models;

/**
 * Created by Ben on 8/2/15.
 */
public class Image {

    /**
     * Base URL for all the images
     */
    private  static final String BASE_URL = "http://media.redmart.com/newmedia/200p";
    private String name;
    private int position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
