// вернуть все элементы
MATCH (a) RETURN a

MATCH (a)-[r]->(b) RETURN a,b,r

MATCH (sch:Scheme)-[r]->(a)
  WHERE (sch.name = 'Configuration N1')
RETURN sch, a, r

// Начальная инициализация базы данных:
// добавление вирусов
CREATE (v1:Virus {name: 'HungryRat'})
SET v1.virus_id = ID(v1);
CREATE (v2:Virus {name: 'Trojan'})
SET v2.virus_id = ID(v2);

// добавление объектов
CREATE (o1:Object {name: 'PC1', type: 1})
SET o1.obj_id = ID(o1);
CREATE (o2:Object {name: 'PC2', type: 1})
SET o2.obj_id = ID(o2);
CREATE (o3:Object {name: 'PC3', type: 1})
SET o3.obj_id = ID(o3);

// заражение (связь объект-[:заражён]->вирус)
MATCH (a), (b)
  WHERE ((a.name = 'HungryRat') and b.name = 'PC3')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;
MATCH (a), (b)
  WHERE ((a.name = 'Trojan') and b.name = 'PC1')
CREATE (b)-[r:INFECTED_BY]->(a)
RETURN a,b,r;

// связка объектов по имени
MATCH (a), (b)
  WHERE ((a.name = 'PC1') and (b.name = 'PC2'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;
MATCH (a), (b)
  WHERE ((a.name = 'PC1') and (b.name = 'PC3'))
CREATE (b)-[r:CONNECTED_TO]->(a)
RETURN a,b,r;

// вернуть все связанные объекты
MATCH (a:Object)-[r:CONNECTED_TO]->(b:Object)
RETURN a,b,r;

// вернуть все заражённые объекты
MATCH (b:Object)-[r:INFECTED_BY]->(a:Virus)
RETURN b;

// добавление схемы
CREATE (sch1:Scheme {name: 'Configuration N1', description: 'The first test configuration system'})
SET sch1.scheme_id = ID(sch1);

// связывание схемы и объектов
MATCH (a:Object), (b:Object), (c:Object), (sch:Scheme)
  WHERE ((a.name = 'PC1') and (b.name = 'PC2') and (c.name = 'PC3') and (sch.name = 'Configuration N1'))
CREATE (sch)-[r1:CONSISTS_OF]->(a)
CREATE (sch)-[r2:CONSISTS_OF]->(b)
CREATE (sch)-[r3:CONSISTS_OF]->(c)
RETURN sch,a,b,c,r1,r2,r3;

// связывание схемы и вирусов
MATCH (a:Virus), (b:Virus), (sch:Scheme)
  WHERE ((a.name = 'HungryRat') and (b.name = 'Trojan') and (sch.name = 'Configuration N1'))
CREATE (sch)-[r1:CONTAINS]->(a)
CREATE (sch)-[r2:CONTAINS]->(b)
RETURN sch,a,b,r1,r2;

//  Вернуть все объекты по названию схемы
MATCH (sch:Scheme), (a:Object)
  WHERE (sch.name = 'Configuration N1')
RETURN sch, a;


//  Вернуть все составляющие по названию схемы!
MATCH (sch:Scheme)-[r]->(a)
  WHERE (sch.name = 'Configuration N1')
RETURN sch, a, r;

// Возможно то, что нужно: ноды и связи, но с какими-то повторениями (!?)
MATCH (s:Scheme)-[con:CONSISTS_OF]->(nod1:Object)-[rel:CONNECTED_TO*]-(nod2:Object)
  WHERE (s.scheme_id=5)
RETURN distinct nod1, rel;

// Разделить на два запроса, а потом UNION?
// Тупо разделить на 2 запроса для нодов и связей?

// Вроде разделил, тут получше
MATCH (s:Scheme)-[con:CONSISTS_OF]->(nod1:Object)-[rel:CONNECTED_TO*]-(nod2:Object)
  WHERE (s.scheme_id=5)
RETURN DISTINCT nod1;

// ВРОДЕ работает
MATCH (s:Scheme)-[con:CONSISTS_OF]->(nod1:Object)-[rel:CONNECTED_TO*]->(nod2:Object)
  WHERE (s.scheme_id=5)
RETURN DISTINCT rel;