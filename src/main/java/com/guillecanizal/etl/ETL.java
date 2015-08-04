package com.guillecanizal.etl;

import com.google.inject.Inject;

/**
 * Created by guillecanizal on 22/06/15.
 */
public abstract class ETL {

    protected Extract extract;
    protected Transform transform;
    protected Load load;
    protected long duration;
    protected int numElements;


    @Inject
    public ETL(Extract extract, Transform transform, Load load) {
        this.extract = extract;
        this.transform = transform;
        this.load = load;
    }

    public abstract void execute();

    public long getDuration() {
        return duration;
    }

    public int getNumElements() {
        return numElements;
    }
}
