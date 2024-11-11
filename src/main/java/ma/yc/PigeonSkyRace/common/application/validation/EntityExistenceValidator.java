package ma.yc.PigeonSkyRace.common.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntityExistenceValidator implements ConstraintValidator<EntityExists, Object> {

    private final MongoTemplate mongoTemplate;
    private Class<?> entityClass;

    @Override
    public void initialize(EntityExists constraintAnnotation) {
        this.entityClass = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            ObjectId objectId = extractObjectId(value);
            log.debug("Validating existence of {} with ID: {}", entityClass.getSimpleName(), objectId);

            boolean exists = mongoTemplate.exists(
                    Query.query(Criteria.where("_id.value").is(objectId)),
                    entityClass
            );

            log.debug("Entity exists: {}", exists);
            return exists;
        } catch (Exception e) {
            log.error("Validation error for entity {}: {}", entityClass.getSimpleName(), e.getMessage());
            return false;
        }
    }

    private ObjectId extractObjectId(Object value) {
        try {
            if (value instanceof Record) {
                return (ObjectId) value.getClass().getMethod("value").invoke(value);
            }
            if (value instanceof String) {
                return new ObjectId((String) value);
            }
            if (value instanceof ObjectId) {
                return (ObjectId) value;
            }
            throw new IllegalArgumentException("Unsupported ID type: " + value.getClass());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract ObjectId", e);
        }
    }
}