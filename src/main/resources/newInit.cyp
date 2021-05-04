//---------
CREATE (sch1:Scheme {name: 'Configuration N1', description: 'Первая конфигурация'})
SET sch1.scheme_id = ID(sch1);
// scheme 1

CREATE (v1:Virus {name: 'HungryRat', description:'Первый созданный вирус!', isInstance: false})
SET v1.virus_id = ID(v1);
CREATE (v2:Virus {name: 'Trojan', description:'Типичный троянский вирус. Очень опасно, знаете ли...', isInstance: false})
SET v2.virus_id = ID(v2);


CREATE (o1:Object {name: 'PC', type: 1, description:'Стандартный пользовательский ПК', isInstance: false})
SET o1.obj_id = ID(o1);


//---------
CREATE (sch1:Scheme {name: 'Configuration N2', description: 'Вторая тестовая конфигурация системы'})
SET sch1.scheme_id = ID(sch1);
// scheme 2

CREATE (v1:Virus {name: 'Worm', description:'Просто вирус-червь. Очень распространённый тип вируса. Может быть опасным, но не очень', isInstance: false})
SET v1.virus_id = ID(v1);
CREATE (v2:Virus {name: 'Predator', description:'Предельно сильный вирус. Берегись!', isInstance: false})
SET v2.virus_id = ID(v2);


CREATE (o:Object {name: 'UserPC', type: 1, description:'Стандартный персональный компьютер пользователя', isInstance: false})
SET o.obj_id = ID(o);

CREATE (o:Object {name: 'WaterPump', type: 2, description:'Водяной насос для охлаждения реактора', isInstance: false})
SET o.obj_id = ID(o);

CREATE (o:Object {name: 'CentralSync', type: 2, description:'Центральная система синхронизации. Чрезвычайно важно!', isInstance: false})
SET o.obj_id = ID(o);


CREATE (s:SecuritySW {name:"KasperskyUltimate", price:1000, description:"Kaspersky ultimate edition от Kaspersky labs. Создан в 2021 году компанией KasperskyLabs", isInstance: false})
SET s.secSW_id = ID(s);

CREATE (s:SecuritySW {name:"WindowsDefender", price:600, description:"WindowsDefender бесплатное встроенное приложение Windows, обеспечивающее безопасность персональных компьютеров. Создан в 2020 году компанией Windows Security labs", isInstance: false})
SET s.secSW_id = ID(s);


CREATE (se: Exploit {name: "SQL", description:"Эксплойт безопасности. SQL-инъекции", isInstance: false})
SET se.SE_id = ID(se);

CREATE (se: Exploit {name: "Skript", description:"Эксплойт безопасности автозапуска скриптов в Windows страых версий", isInstance: false})
SET se.SE_id = ID(se);

CREATE (se: Exploit {name: "Stack overflow", description:"Эксплойт безопасности переполнения стека", isInstance: false})
SET se.SE_id = ID(se);