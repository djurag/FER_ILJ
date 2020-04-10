grammar Request;
@header{
package hr.tel.fer.ilj.lab.lab1.antlr;
}

request: 'FILTER' WS (expr WS)* 'RETURN' num;
expr: KEY WS*? OP WS*? value;
value: STRING;
num: WS*? STRING;

KEY: ('IP' | 'DATETIME' | 'METHOD' | 'VERSION' | 'STATUS');
OP: ('!=' | '==');
ESC : '\\"' ;
STRING: '"' (ESC|.)*? '"' ;
WS: ' ';