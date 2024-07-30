package POJOClasses;

public class RequestBody2 {
	private Entity1 entity1;
    private Address physical;
    private Mailing mailing;
    private Contact entityContact;
    private Identifiers identifiers;

    // Getters and Setters
    public Entity1 getEntity() {
        return entity1;
    }

    public void setEntity(Entity1 entity) {
        this.entity1 = entity;
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
