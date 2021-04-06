CREATE (v1:Virus {name: 'HungryRat'})
SET v1.virus_id = ID(v1);
CREATE (v2:Virus {name: 'Trojan'})
SET v2.virus_id = ID(v2);

CREATE (o1:Object {name: 'PC1', type: 1})
SET o1.obj_id = ID(o1);
CREATE (o2:Object {name: 'PC2', type: 1})
SET o2.obj_id = ID(o2);
CREATE (o3:Object {name: 'PC3', type: 1})
SET o3.obj_id = ID(o3);

MATCH (a), (b)
  WHERE ((a.name = 'HungryRat') and b.name = 'PC3')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;
MATCH (a), (b)
  WHERE ((a.name = 'Trojan') and b.name = 'PC1')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;

MATCH (a), (b)
  WHERE ((a.name = 'PC1') and (b.name = 'PC2'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;
MATCH (a), (b)
  WHERE ((a.name = 'PC1') and (b.name = 'PC3'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

CREATE (sch1:Scheme {name: 'Configuration N1', description: 'The first test configuration system'})
SET sch1.scheme_id = ID(sch1);

MATCH (a:Object), (b:Object), (c:Object), (sch:Scheme)
  WHERE ((a.name = 'PC1') and (b.name = 'PC2') and (c.name = 'PC3') and (sch.name = 'Configuration N1'))
CREATE (sch)-[r1:CONSISTS_OF]->(a)
CREATE (sch)-[r2:CONSISTS_OF]->(b)
CREATE (sch)-[r3:CONSISTS_OF]->(c)
RETURN sch,a,b,c,r1,r2,r3;

MATCH (a:Virus), (b:Virus), (sch:Scheme)
  WHERE ((a.name = 'HungryRat') and (b.name = 'Trojan') and (sch.name = 'Configuration N1'))
CREATE (sch)-[r1:CONTAINS]->(a)
CREATE (sch)-[r2:CONTAINS]->(b)
RETURN sch,a,b,r1,r2;

