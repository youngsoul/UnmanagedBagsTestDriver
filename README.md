UnmanagedBagsTestDriver
=======================

There are a couple of integration tests to show how this is used.  For the ParentCarsIntegrationTests here is the
output with SQL to see how / what is being done at the database level

```
Create Parent and save
Hibernate:
    insert
    into
        parent
        (id, version, name)
    values
        (null, ?, ?)
After parent save
Before addToCars with unsaved Car
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
After addToCars with unsaved Car
Before addToCars with unsaved Car
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
After addToCars with unsaved Car
Before addToCars with unsaved Car
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
After addToCars with unsaved Car
Before count of Parent and Car objects
Hibernate:
    select
        count(*) as y0_
    from
        parent this_
Hibernate:
    select
        count(*) as y0_
    from
        car this_
After count of Parent and Car objects
Before parent.getCars
Hibernate:
    select
        this_.id as id0_0_,
        this_.version as version0_0_,
        this_.name as name0_0_,
        this_.owner_id as owner4_0_0_
    from
        car this_
    where
        this_.owner_id=?
Before parent.getCars
[fast, mini van, gig car]
Before removeFromCars(car2)
Hibernate:
    update
        car
    set
        version=?,
        name=?,
        owner_id=?
    where
        id=?
        and version=?
After removeFromCars(car2)
Before count of Parent and Car objects and the parents cars
Hibernate:
    select
        count(*) as y0_
    from
        parent this_
Hibernate:
    select
        count(*) as y0_
    from
        car this_
Hibernate:
    select
        this_.id as id0_0_,
        this_.version as version0_0_,
        this_.name as name0_0_,
        this_.owner_id as owner4_0_0_
    from
        car this_
    where
        this_.owner_id=?
After count of Parent and Car objects
Before removeAllCars()
Hibernate:
    update
        car
    set
        owner_id=null
    where
        owner_id=?
After removeAllCars()
Before count of Parent and Car objects and the parents cars
Hibernate:
    select
        count(*) as y0_
    from
        parent this_
Hibernate:
    select
        count(*) as y0_
    from
        car this_
Hibernate:
    select
        this_.id as id0_0_,
        this_.version as version0_0_,
        this_.name as name0_0_,
        this_.owner_id as owner4_0_0_
    from
        car this_
    where
        this_.owner_id=?
After count of Parent and Car objects
Before setCars with a list of 4 new cars and zero existing cars
Hibernate:
    update
        car
    set
        owner_id=null
    where
        owner_id=?
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
After setCars with a list of 4 new cars
Before count of Parent and Car objects and the parents cars
Hibernate:
    select
        count(*) as y0_
    from
        parent this_
Hibernate:
    select
        count(*) as y0_
    from
        car this_
Hibernate:
    select
        this_.id as id0_0_,
        this_.version as version0_0_,
        this_.name as name0_0_,
        this_.owner_id as owner4_0_0_
    from
        car this_
    where
        this_.owner_id=?
After count of Parent and Car objects
Before setCars with a list of 2 new cars and 4 existing cars
Hibernate:
    update
        car
    set
        owner_id=null
    where
        owner_id=?
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
Hibernate:
    insert
    into
        car
        (id, version, name, owner_id)
    values
        (null, ?, ?, ?)
After setCars with a list of 2 new cars
Before count of Parent and Car objects and the parents cars
Hibernate:
    select
        count(*) as y0_
    from
        parent this_
Hibernate:
    select
        count(*) as y0_
    from
        car this_
Hibernate:
    select
        this_.id as id0_0_,
        this_.version as version0_0_,
        this_.name as name0_0_,
        this_.owner_id as owner4_0_0_
    from
        car this_
    where
        this_.owner_id=?
After count of Parent and Car objects

Before deleteAll cars associated with parent
Hibernate:
    delete
    from
        car
    where
        owner_id=?
Before deleteAll cars associated with parent
Before count of Parent and Car objects and the parents cars
Hibernate:
    select
        count(*) as y0_
    from
        parent this_
Hibernate:
    select
        count(*) as y0_
    from
        car this_
Hibernate:
    select
        this_.id as id0_0_,
        this_.version as version0_0_,
        this_.name as name0_0_,
        this_.owner_id as owner4_0_0_
    from
        car this_
    where
        this_.owner_id=?
After count of Parent and Car objects

```
