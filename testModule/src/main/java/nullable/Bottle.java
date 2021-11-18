package nullable;

/**
 * @author sxh
 * @date 2021/11/18
 */
public class Bottle {
    private int totalCapacity;

    private int usedCapacity;

    private boolean canKeepWarm;

    public Bottle() {
    }

    public Bottle(int totalCapacity, int usedCapacity, boolean canKeepWarm) {
        this.totalCapacity = totalCapacity;
        this.usedCapacity = usedCapacity;
        this.canKeepWarm = canKeepWarm;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public boolean isCanKeepWarm() {
        return canKeepWarm;
    }

    public void setCanKeepWarm(boolean canKeepWarm) {
        this.canKeepWarm = canKeepWarm;
    }

    @Override
    public String toString() {
        return "Bottle{" + "totalCapacity=" + totalCapacity + ", usedCapacity=" + usedCapacity + ", canKeepWarm=" + canKeepWarm + '}';
    }
}
