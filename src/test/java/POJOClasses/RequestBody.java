package POJOClasses;

public class RequestBody {
    private Entity entity;
    private Address physical;
    private Mailing mailing;
    private Contact entityContact;
    private Identifiers identifiers;

    // Getters and Setters
    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Address getPhysical() {
        return physical;
    }

    public void setPhysical(Address physical) {
        this.physical = physical;
    }

    public Mailing getMailing() {
        return mailing;
    }

    public void setMailing(Mailing mailing) {
        this.mailing = mailing;
    }

    public Contact getEntityContact() {
        return entityContact;
    }

    public void setEntityContact(Contact entityContact) {
        this.entityContact = entityContact;
    }

    public Identifiers getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }
      
}
