package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorRepository extends ReactiveMongoRepository<Editor, ObjectId> {
}
