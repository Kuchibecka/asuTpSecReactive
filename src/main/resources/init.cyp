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


// ___________________NEW INIT SCHEME___________________
CREATE (v1:Virus {name: 'Worm'})
SET v1.virus_id = ID(v1);
CREATE (v2:Virus {name: 'Predator'})
SET v2.virus_id = ID(v2);

CREATE (o:Object {name: 'UserPC1', type: 1})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC2', type: 1})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC3', type: 1})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC4', type: 1})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC5', type: 1})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC6', type: 1})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'WaterPump1', type: 2})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'WaterPump2', type: 2})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'CentralSync', type: 2})
SET o.obj_id = ID(o);


MATCH (a:Virus) WITH a
MATCH (b:Virus)
  WHERE ((a.name = 'Worm') and b.name = 'UserPC1')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;

MATCH (a:Virus) WITH a
MATCH (b:Virus)
  WHERE ((a.name = 'Predator') and b.name = 'UserPC6')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;


MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC1') and (b.name = 'UserPC2'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC2') and (b.name = 'UserPC3'))
CREATE (a)-[r:CONNECTED_TO]->(b)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC3') and (b.name = 'UserPC4'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC3') and (b.name = 'UserPC5'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC4') and (b.name = 'UserPC5'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC4') and (b.name = 'UserPC6'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC5') and (b.name = 'UserPC6'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC2') and (b.name = 'WaterPump1'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC5') and (b.name = 'WaterPump2'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

MATCH (a:Object) WITH a
MATCH (b:Object)
  WHERE ((a.name = 'UserPC3') and (b.name = 'CentralSync'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;


MATCH (a:Virus), (b:Object)
  WHERE ((a.name = 'Worm') and b.name = 'UserPC1')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;


MATCH (a:Virus), (b:Object)
  WHERE ((a.name = 'Predator') and b.name = 'UserPC6')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;


CREATE (sch1:Scheme {name: 'Configuration N2', description: 'The second test configuration system'})
SET sch1.scheme_id = ID(sch1);


MATCH (a:Object) WITH a
MATCH (sch:Scheme)
  WHERE ((a.name =~ 'UserPC.*') and (sch.name = 'Configuration N2'))
CREATE (sch)-[r1:CONSISTS_OF]->(a)
RETURN sch,a,r1;


MATCH (v:Virus) WITH v
MATCH (sch:Scheme)
  WHERE ((v.name ="Predator" or v.name ="Worm") and sch.name = "Configuration N2")
CREATE (sch)-[r1:CONTAINS]->(v)
RETURN sch,r1,v;


CREATE (s:SecuritySW {name:"KasperskyUltimate", price:1000, description:"Kaspersky ultimate edition by Kaspersky labs. Created in 2021 by KasperskyLabs"})
SET s.secSW_id = ID(s);

CREATE (s:SecuritySW {name:"WindowsDefender", price:600, description:"WindowsDefender free built-in Windows app providing security of personal computers. Created in 2020 by Windows Security labs"})
SET s.secSW_id = ID(s);


MATCH (o:Object) WITH o
MATCH (s:SecuritySW)
  WHERE (o.name ="UserPC3" and s.name ="WindowsDefender")
CREATE (o)-[r1:PROTECTED_BY]->(s)
RETURN s,r1,o;

MATCH (o:Object) WITH o
MATCH (s:SecuritySW)
  WHERE (o.name ="UserPC5" and s.name ="KasperskyUltimate")
CREATE (o)-[r1:PROTECTED_BY]->(s)
RETURN s,r1,o;


CREATE (se: Exploit {name: "SQL", description:"An SQL-injection security exploit"})
SET se.SE_id = ID(se);

CREATE (se: Exploit {name: "Skript", description:"Windows skript autorun security exploit"})
SET se.SE_id = ID(se);

CREATE (se: Exploit {name: "Stack overflow", description:"Stack overflow security exploit"})
SET se.SE_id = ID(se);


MATCH (e:Exploit) WITH e
MATCH (s:SecuritySW)
  WHERE ((e.name ="SQL" or e.name ="Skript" or e.name ="Stack overflow") and s.name ="KasperskyUltimate")
CREATE (s)-[r1:PREVENTS_EXPLOIT]->(e)
RETURN s,r1,e;

MATCH (e:Exploit) WITH e
MATCH (s:SecuritySW)
  WHERE ((e.name ="SQL" or e.name ="Skript") and s.name ="WindowsDefender")
CREATE (s)-[r1:PREVENTS_EXPLOIT]->(e)
RETURN s,r1,e;


MATCH (e:Exploit) WITH e
MATCH (v:Virus)
  WHERE ((e.name ="SQL" or e.name ="Skript" or e.name ="Stack overflow") and v.name ="Predator")
CREATE (v)-[r1:USES_EXPLOIT]->(e)
RETURN v,r1,e;

MATCH (e:Exploit) WITH e
MATCH (v:Virus)
  WHERE (e.name ="SQL" or v.name ="Worm")
CREATE (v)-[r1:USES_EXPLOIT]->(e)
RETURN v,r1,e;
