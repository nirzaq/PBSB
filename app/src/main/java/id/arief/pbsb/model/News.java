package id.arief.pbsb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by arief on 15/02/16.
 */
public class News implements Serializable {
    private static final long serialVersionUID = 111696345129311948L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image_thumbnail")
    @Expose
    private String imageThumbnail;
    @SerializedName("image_full")
    @Expose
    private String imageFull;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("date_id")
    @Expose
    private String dateId;

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The imageThumbnail
     */
    public String getImageThumbnail() {
        return imageThumbnail;
    }

    /**
     *
     * @param imageThumbnail
     *     The image_thumbnail
     */
    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    /**
     *
     * @return
     *     The imageFull
     */
    public String getImageFull() {
        return imageFull;
    }

    /**
     *
     * @param imageFull
     *     The image_full
     */
    public void setImageFull(String imageFull) {
        this.imageFull = imageFull;
    }

    /**
     *
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     *     The dateId
     */
    public String getDateId() {
        return dateId;
    }

    /**
     *
     * @param dateId
     *     The date_id
     */
    public void setDateId(String dateId) {
        this.dateId = dateId;
    }
}
