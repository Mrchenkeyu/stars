package com.stars.util.backdoor.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BackdoorRow {
    private final List<com.stars.util.backdoor.result.BackdoorCell> cells;
    private final int index;
    
    public BackdoorRow(int index) {
        this.cells = new ArrayList<com.stars.util.backdoor.result.BackdoorCell>();
        this.index = index;
    }
    
    public void addCell(com.stars.util.backdoor.result.BackdoorCell cell) {
        this.cells.add(cell);
    }
    
    public void addCell(int index, com.stars.util.backdoor.result.BackdoorCell cell) {
        this.cells.add(index, cell);
    }
    
    public com.stars.util.backdoor.result.BackdoorCell getCell(int index) {
        return this.cells.get(index);
    }
    
    public Iterator<BackdoorCell> cellIterator() {
        return this.cells.iterator();
    }
    
    public int size() {
        return this.cells.size();
    }
    
    public int index() {
        return this.index;
    }
}
