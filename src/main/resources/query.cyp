MATCH (a) RETURN a

MATCH (a)-[r]->(b) RETURN a,b,r

MATCH (sch:Scheme)-[r]->(a)
  WHERE (sch.name = 'Configuration N1')
RETURN sch, a, r