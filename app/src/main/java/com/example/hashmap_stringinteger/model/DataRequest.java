package com.example.hashmap_stringinteger.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataRequest implements Serializable {
    private String map_structure;
    private String data_type;
    private long size_array;
    private int insertion;
    private int iteration;
    private int random_query;
    private int deletion;
    private int _memory_usage;

//    public DataRequest(String map_structure, String data_type, long size_array, int insertion, int iteration, int random_query, int deletion, int _memory_usage) {
//        this.map_structure = map_structure;
//        this.data_type = data_type;
//        this.size_array = size_array;
//        this.insertion = insertion;
//        this.iteration = iteration;
//        this.random_query = random_query;
//        this.deletion = deletion;
//        this._memory_usage = _memory_usage;
//    }

    public String getMap_structure() {
        return map_structure;
    }

    public void setMap_structure(String map_structure) {
        this.map_structure = map_structure;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public long getSize_array() {
        return size_array;
    }

    public void setSize_array(long size_array) {
        this.size_array = size_array;
    }

    public int getInsertion() {
        return insertion;
    }

    public void setInsertion(int insertion) {
        this.insertion = insertion;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getRandom_query() {
        return random_query;
    }

    public void setRandom_query(int random_query) {
        this.random_query = random_query;
    }

    public int getDeletion() {
        return deletion;
    }

    public void setDeletion(int deletion) {
        this.deletion = deletion;
    }

    public int get_memory_usage() {
        return _memory_usage;
    }

    public void set_memory_usage(int _memory_usage) {
        this._memory_usage = _memory_usage;
    }
    //    {
//        "map_structure": "hashMap",
//            "data_type": "s-i",
//            "size_array": 1000,
//            "insertion": 7,
//            "iteration": 12,
//            "random_query": 25,
//            "deletion": 8,
//            "_memory_usage": 320
//    }

}
