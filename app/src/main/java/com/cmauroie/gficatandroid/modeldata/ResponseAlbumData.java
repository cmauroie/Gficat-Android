package com.cmauroie.gficatandroid.modeldata;

import java.util.ArrayList;

/**
 * Created by Mauricio on 26/8/17.
 */

public class ResponseAlbumData {
    private String id;
    private int published;
    private ArrayList<ModelDataAlbum> nodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public ArrayList<ModelDataAlbum> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<ModelDataAlbum> nodes) {
        this.nodes = nodes;
    }
}
