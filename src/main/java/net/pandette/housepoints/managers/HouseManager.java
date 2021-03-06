package net.pandette.housepoints.managers;

import net.pandette.housepoints.config.Configuration;
import net.pandette.housepoints.dtos.House;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class HouseManager {

    private final Map<String, House> houseMap;
    private final Configuration configuration;

    @Inject
    public HouseManager(Configuration configuration) {
        houseMap = new HashMap<>();
        this.configuration = configuration;
    }

    public List<House> getHouses() {
        return new ArrayList<>(houseMap.values());
    }

    public House getHouse(String s) {
        return houseMap.get(s.toLowerCase());
    }

    public void load() {
        configuration.loadKeys("Houses", (c, s) -> houseMap.put(s.toLowerCase(), configuration.getHouse(c, s)));
    }

    public void reload() {
        houseMap.clear();
        load();
    }

    public void save() {
        configuration.saveHouses(houseMap.values());
    }


}
