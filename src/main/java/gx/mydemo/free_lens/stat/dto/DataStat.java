package gx.mydemo.free_lens.stat.dto;

import lombok.Getter;

@Getter
public class DataStat {
    int total_count;

    public DataStat(int total_count) {
        this.total_count = total_count;
    }

}
