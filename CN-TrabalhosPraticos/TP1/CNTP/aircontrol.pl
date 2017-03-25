% Air control speculative

% ------ Operators ------

:- op(400, xfx, @).

% ------ Answer Conversion ------

%Predicate used to convert the answer from the information sources into literals.

%Fontes de Informação
  %  t@mi
  %  p@pi
  %  o@oi


answer_conversion(t,mi,in_set,Value):-
										assert(answer(t,constraint(t(T)@mi,T,expression(in_set([Value]),list)))).
answer_conversion(p,pi,in_set,Value) :-
										assert(answer(p,constraint(p(P)@pi,P,expression(in_set([Value]),list)))).
answer_conversion(o,oi,in_set,Value) :-
										assert(answer(o,constraint(o(O)@oi,O,expression(in_set([Value]),list)))).

% ------ Default Conversion ------

%Predicate iused to convert the initial defaults from the cdss

initial_default_conversion(t,in_set,Value):-
										assert(delta(t(T)@mi,T,expression(in_set([Value]),list))).
initial_default_conversion(p,in_set,Value) :-
										assert(delta(p(P)@pi,P,expression(in_set([Value]),list))).
initial_default_conversion(o,in_set,Value) :-
										assert(delta(o(O)@oi,O,expression(in_set([Value]),list))).

%Predicate used to convert the new defaults from the cdss into literals.

default_conversion(t,mi,in_set,Value):-
										(new_delta(t,constraint(t(T)@mi,T,expression(in_set([NotValue]),list)))-> retract(new_delta(t,constraint(t(T)@mi,T,expression(in_set([NotValue]),list))))
																												;
										true),

										assert(new_delta(t,constraint(t(T)@mi,T,expression(in_set([Value]),list)))).
default_conversion(p,pi,in_set,Value) :-
										(new_delta(p,constraint(p(P)@pi,P,expression(in_set([NotValue]),list)))-> retract(new_delta(p,constraint(p(P)@pi,P,expression(in_set([NotValue]),list))))
																												;
										true),

										assert(new_delta(p,constraint(p(P)@pi,P,expression(in_set([Value]),list)))).
default_conversion(o,oi,in_set,Value) :-
										(new_delta(o,constraint(o(O)@oi,O,expression(in_set([NotValue]),list)))-> retract(new_delta(o,constraint(o(O)@oi,O,expression(in_set([NotValue]),list))))
																												;
										true),
										assert(new_delta(o,constraint(o(O)@oi,O,expression(in_set([Value]),list)))).

% ------ External Predicates ------

askable(t(_)@mi). %t@mi
askable(p(_)@pi). %p@pi
askable(o(_)@oi). %o@oi

ask_format(t(_)@mi,t,mi).
ask_format(p(_)@pi,p,pi).
ask_format(o(_)@oi,o,oi).

% ------ Default Set ------
% 𝚫 - valores por defeito
%    t(T)@mi <- Tϵ{t0} ||.
%    p(P)@pi <- P ϵ p={p0} ||.
%    o(O)@oi <- O ϵ {o1} ||.


delta(t(T)@mi,T,expression(in_set([t0]),list)).
delta(p(P)@pi,P,expression(in_set([p0]),list)).
delta(o(O)@oi,O,expression(in_set([o1]),list)).

% ------ Program P ------

%%alt(question1, F) <- F € {action1}.
%%alt(question1, F) <- F € {action2}.
%%alt(question1, F) <- F € {action3}.

rule(nt(X,F),[alt(X,F),tcv(F)]).

% tcv(F) <- F € {action1}, T € {t1}, O € {o1}, P € {p0} || t(T)@mi, o(O)@oi, p@pi.
rule(tcv(action1),
			[constraint(t(T)@mi,T, [expression(in_set([t0]),list)]),
			constraint(p(P)@pi,P,[expression(in_set([p0]),list)]),
			constraint(o(O)@oi,O,[expression(in_set([o1]),list)])]).
% tcv(F) <- F € {action2}, T € {t1}, O € (o1), P € {p1} || t(T)@mi, o(O)@oi, p@pi.
rule(tcv(action2),
			[constraint(t(T)@mi,T,[expression(in_set([t0]),list)]),
			constraint(p(P)@pi,P,[expression(in_set([p1]),list)])]).
% tcv(F) <- F € {action2}, T € {t1}, O € (o2) || t(T)@mi, o(O)@oi.
rule(tcv(action2),
			[constraint(t(T)@mi,T,[expression(in_set([t0]),list)]),
			constraint(o(O)@oi,O,[expression(in_set([o2]),list)])]).
% tcv(F) <- F € {action2}, T € {t2} || t(T)@mi.
rule(tcv(action2),
			[constraint(t(T)@mi,T,[expression(in_set([t1]),list)])]).
% tcv(F) <- F € {action3}, T € {t3} || t(T)@mi.
rule(tcv(action3),
			[constraint(t(T)@mi,T,[expression(in_set([t2]),list)])]).

fact(alt(question1,action1)).
fact(alt(question1,action2)).
fact(alt(question1,action3)).


%active(process(23, [1, 2, 17, 18, 19, 21], [], [t(_)@ois, n(_)@ois, m(_)@ois], [], [constraint(t(A)@ois, A, [expression(in_set([t3]), list)], default), constraint(n(B)@ois, B, [expression(in_set([n2]), list)], default), constraint(m(C)@ois, C, [expression(in_set([m1]), list)], default)], nt(question1, action5))).

%suspended(n(A)@ois, process(8, [1, 2, 6, 7], [constraint(n(A)@ois, A, [expression(in_set([n0]), list)]), constraint(m(B)@ois, B, [expression(in_set([m0]), list)])], [t(_)@ois], [], [constraint(t(C)@ois, C, [expression(in_set([t3]), list)], default)], nt(question1, action2))).

%process(8, [1, 2, 6, 7], [constraint(n(A)@ois, A, [expression(in_set([n0]), list)]), constraint(m(B)@ois, B, [expression(in_set([m0]), list)])], [t(_)@ois], [], [constraint(t(C)@ois, C, [expression(in_set([t3]), list)], default)], nt(question1, action2)).

%process(23, [1, 2, 17, 18, 19, 21], [], [t(_)@ois, n(_)@ois, m(_)@ois], [], [constraint(t(A)@ois, A, [expression(in_set([t3]), list)], default), constraint(n(B)@ois, B, [expression(in_set([n2]), list)], default), constraint(m(C)@ois, C, [expression(in_set([m1]), list)], default)], nt(question1, action5)).
