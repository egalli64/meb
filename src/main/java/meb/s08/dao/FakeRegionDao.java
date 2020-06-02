package meb.s08.dao;

import java.util.Optional;

import javax.enterprise.inject.Model;

@Model
public class FakeRegionDao {
    public Optional<Region> read(Integer id) {
        return Optional.of(new Region("Fake"));
    }
}
