package com.entertainment;

import java.util.Objects;

/*
 * Natural order is defined by 'brand' (String).
 *
 * To be "consistent with equals," whatever fields are used for "equals" (brand, volume)
 * MUST ALSO BE USED for "compareTo".
 */
public class Television implements Comparable<Television> {
    private String brand;
    private int volume;

    // Television HAS-A Tuner
    private final Tuner tuner = new Tuner();  // instantiated internally, not exposed (no get/set)

    public Television() {
    }

    public Television(String brand, int volume) {
        setBrand(brand);
        setVolume(volume);
    }

    public int getCurrentChannel() {
        return tuner.getChannel();  // delegate to contained Tuner object
    }

    public void changeChannel(int channel) {
        tuner.setChannel(channel);  // delegate to contained Tuner object
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int hashCode() {
        // a poorly written hash function, it easily results in "hash collisions,"
        // i.e., "different" objects can easily yield the same hashcode: "Sony" 50 and "LG" 52
        // return getBrand().length() + getVolume();

        // instead, we use Objects.hash() to give us a "scientifically correct" hash function
        // it will minimize the probability of hash collisions
        return Objects.hash(getBrand(), getVolume());  // MUST use same fields as equals()
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Television that = (Television) obj;
        return this.getVolume() == that.getVolume() &&
               Objects.equals(this.getBrand(), that.getBrand());
    }
    */

    @Override
    // tvA.equals(tvB)
    public boolean equals(Object obj) {
        boolean result = false;

        // 'this' (me) and 'obj' refer to the same physical object in memory!
        if (this == obj) {
            result = true;  // and we're done
        }
        // 'obj' is not-null and my Class object is the same as its Class object, so proceed
        // otherwise, skip this whole thing and that means return result (false)
        else if (obj != null && this.getClass() == obj.getClass()) {
            // downcast 'obj' to more specific reference type Television, to call getBrand(), etc.
            Television other = (Television) obj;

            // do the checks - sameness is defined as same-brand AND same-volume
            // Objects.equals() does a "null-safe" on the two arguments provided (Java 7)
            result = Objects.equals(this.getBrand(), other.getBrand()) &&
                     this.getVolume() == other.getVolume();  // primitives can't be null
        }
        return result;
    }

    /*
     * Natural order is defined by 'brand' (String),
     * and secondarily by 'volume' (int) when tied on brand.
     */
    @Override
    public int compareTo(Television other) {
        int result = this.getBrand().compareTo(other.getBrand());

        if (result == 0) {  // tied on brand, so break the tie by volume
            result = Integer.compare(this.getVolume(), other.getVolume());
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s: brand=%s, volume=%s, currentChannel=%s",
                getClass().getSimpleName(), getBrand(), getVolume(), getCurrentChannel());
    }
}