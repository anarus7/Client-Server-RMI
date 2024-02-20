package ro.ubb.lab3.server.service;

import ro.ubb.lab3.common.BaseEntity;
import ro.ubb.lab3.common.EntityService;
import ro.ubb.lab3.server.repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;


    public class GenericService<T extends BaseEntity<Long>> implements EntityService<T> {
        protected final Repository<Long, T> entityRepository;
        ExecutorService executorService;


        public GenericService(Repository<Long, T> entityRepository, ExecutorService executorService) {
            this.entityRepository = entityRepository;
            this.executorService = executorService;
        }

        @Override
        public List<T> getAllEntities() {
            try {

                return executorService.submit(() -> {
                    List<T> entities = new ArrayList<>();

                    this.entityRepository.findAll().forEach(entities::add);
                    return entities;

                }).get();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return Collections.emptyList();
        }

        @Override
        public T readOneEntity(Long id) throws ExecutionException, InterruptedException {
            return executorService.submit(() -> this.entityRepository.findOne(id)).get().get();
        }

        @Override
        public T addEntity(T entity) throws ExecutionException, InterruptedException {
            return executorService.submit(() -> this.entityRepository.save(entity)).get().orElse(null);
        }

        @Override
        public T updateEntity(T entity) {
            Optional<T> result = Optional.empty();
            try {
                result = this.entityRepository.update(entity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return result.orElse(null);
        }

        @Override
        public void deleteOneEntity(Long id) {
            try {
                this.entityRepository.delete(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
