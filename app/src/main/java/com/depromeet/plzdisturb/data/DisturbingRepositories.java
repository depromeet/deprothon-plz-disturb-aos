package com.depromeet.plzdisturb.data;

public class DisturbingRepositories {

    private static DisturbingRepository repository;

    public static DisturbingRepository getRepository() {
        if (repository == null) {
            repository = new DisturbingRepositoryImpl();
        }

        return repository;
    }
}
