CREATE (v1:Virus {name: 'HungryRat', description:'Первый созданный вирус!'})
SET v1.virus_id = ID(v1);
CREATE (v2:Virus {name: 'Trojan', description:'Типичный троянский вирус. Очень опасно, знаете ли...'})
SET v2.virus_id = ID(v2);

CREATE (o1:Object {name: 'PC1', type: 1, description:'Стандартный пользовательский ПК номер 1'})
SET o1.obj_id = ID(o1);
CREATE (o2:Object {name: 'PC2', type: 1, description:'Стандартный пользовательский ПК номер 2'})
SET o2.obj_id = ID(o2);
CREATE (o3:Object {name: 'PC3', type: 1, description:'Стандартный пользовательский ПК номер 3'})
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

CREATE (sch1:Scheme {name: 'Configuration N1', description: 'Первая конфигурация'})
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
CREATE (v1:Virus {name: 'Worm', description:'Просто вирус-червь. Очень распространённый тип вируса. Может быть опасным, но не очень'})
SET v1.virus_id = ID(v1);
CREATE (v2:Virus {name: 'Predator', description:'Предельно сильный вирус. Берегись!'})
SET v2.virus_id = ID(v2);

CREATE (o:Object {name: 'UserPC1', type: 1, description:'Стандартный персональный компьютер пользователя № 1'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC2', type: 1, description:'Стандартный персональный компьютер пользователя № 2'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC3', type: 1, description:'Стандартный персональный компьютер пользователя № 3'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC4', type: 1, description:'Стандартный персональный компьютер пользователя № 4'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC5', type: 1, description:'Стандартный персональный компьютер пользователя № 5'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'UserPC6', type: 1, description:'Стандартный персональный компьютер пользователя № 6'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'WaterPump1', type: 2, description:'Водяной насос № 1 для охлаждения реактора'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'WaterPump2', type: 2, description:'Водяной насос № 2 для охлаждения реактора'})
SET o.obj_id = ID(o);
CREATE (o:Object {name: 'CentralSync', type: 2, description:'Центральная система синхронизации. Чрезвычайно важно!'})
SET o.obj_id = ID(o);


MATCH (sch:Scheme) WITH sch
MATCH (a:Object)
  WHERE (a.name = 'WaterPump1' and sch.name = 'Configuration N2')
CREATE (sch)-[:CONSISTS_OF]->(a);

MATCH (sch:Scheme) WITH sch
MATCH (a:Object)
  WHERE (a.name = 'WaterPump2' and sch.name = 'Configuration N2')
CREATE (sch)-[:CONSISTS_OF]->(a);

MATCH (sch:Scheme) WITH sch
MATCH (a:Object)
  WHERE (a.name = 'CentralSync' and sch.name = 'Configuration N2')
CREATE (sch)-[:CONSISTS_OF]->(a);


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


CREATE (sch1:Scheme {name: 'Configuration N2', description: 'Вторая тестовая конфигурация системы'})
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


CREATE (s:SecuritySW {name:"KasperskyUltimate", price:1000, description:"Kaspersky ultimate edition от Kaspersky labs. Создан в 2021 году компанией KasperskyLabs"})
SET s.secSW_id = ID(s);

CREATE (s:SecuritySW {name:"WindowsDefender", price:600, description:"WindowsDefender бесплатное встроенное приложение Windows, обеспечивающее безопасность персональных компьютеров. Создан в 2020 году компанией Windows Security labs"})
SET s.secSW_id = ID(s);


MATCH (sw:SecuritySW) WITH sw
MATCH (sch:Scheme)
  WHERE ((sw.name ='KasperskyUltimate' OR sw.name ='WindowsDefender') AND sch.name = 'Configuration N2')
CREATE (sch)-[r1:SECURED_BY]->(sw)
RETURN sch, r1, sw;


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


CREATE (se: Exploit {name: "SQL", description:"Эксплойт безопасности. SQL-инъекции"})
SET se.SE_id = ID(se);

CREATE (se: Exploit {name: "Skript", description:"Эксплойт безопасности автозапуска скриптов в Windows страых версий"})
SET se.SE_id = ID(se);

CREATE (se: Exploit {name: "Stack overflow", description:"Эксплойт безопасности переполнения стека"})
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


MATCH (sch:Scheme) WITH sch
MATCH (o:Object)
  WHERE ((sch.name = "Configuration N2") AND (o.name = "WaterPump1" OR o.name = "WaterPump2" OR o.name = "CentralSync"))
CREATE (sch)-[:FAILS_AT]->(o)
RETURN sch, o;

MATCH (o:Object) WITH o
MATCH (o1:Object)
  WHERE (o.name = "WaterPump1" AND o1.name = "WaterPump2")
CREATE (o1)-[:AND]->(o)
RETURN o1, o;