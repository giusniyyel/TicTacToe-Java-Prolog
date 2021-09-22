% Utilización de Minmax por partes.

%------------------Implementacion MinMax-------------------------------%
% Necesitamos implementar utility, move, min_to_move, max_to_move
minimax(Pos, BestNextPos, Val) :-             % Pos tiene sucesores
    bagof(NextPos, move(Pos, NextPos), NextPosList),
    best(NextPosList, BestNextPos, Val), !.

minimax(Pos, _, Val) :-                        % Pos no tiene sucesores
    utility(Pos, Val).

best([Pos], Pos, Val) :-                       % No hay otra posición para comparar
    minimax(Pos, _, Val), !.

best([Pos1 | PosList], BestPos, BestVal) :-    % Hay otras posiciones para comparar
    minimax(Pos1, _, Val1),
    best(PosList, Pos2, Val2),
    betterOf(Pos1, Val1, Pos2, Val2, BestPos, BestVal).

betterOf(Pos0, Val0, _, Val1, Pos0, Val0) :-   % Pos0 mejor que Pos1
    min_to_move(Pos0),                         % MIN pasa a Pos0
    Val0 > Val1, !.                            % MAX obtiene el mejor valor

betterOf(Pos0, Val0, _, Val1, Pos0, Val0) :-   % Pos0 mejor que Pos1
    max_to_move(Pos0),                         % MAX pasa a Pos0
    Val0 < Val1, !.                            % MIN obtiene el menor valor

betterOf(_, _, Pos1, Val1, Pos1, Val1).        % De lo contrario, Pos1 será mejor que Pos0

%---------------Definiendo utility, move, min_to_move, max_to_move-----------------%
min_to_move([o,_,_]).
max_to_move([x,_,_]).

utility([o,win,_], 1). % La computadora es min y El Humano es max.
utility([x,win,_], -1).
utility([_,tie,_], 0).

% En caso de haber empate/gato, la funci�n member verifica si hayespacios vacios, si no, es un empate/gato
tieGame(_,Board):-
	\+ member(0, Board).

move([P1, start_it, Board], [P2, win, NextBoard]) :-
    nextPlayer(P1, P2),
    move_helper(P1, Board, NextBoard),
    win(NextBoard, P1), !. % verifica el estado de board si hay un estado ganador, si lo hay, termina.

move([P1, start_it, Board], [P2, tie, NextBoard]) :-
    nextPlayer(P1, P2),
    move_helper(P1, Board, NextBoard),
    tieGame(P1,NextBoard), !. % verifica el estado de board si hay un estado de empate, si lo hay, termina.

move([P1, start_it, Board], [P2, start_it, NextBoard]) :-
    nextPlayer(P1, P2),
    move_helper(P1, Board, NextBoard). % si no hay ni una ganada, ni un empate, el juego continua.

% interaccion con posibles movimientos para ver cuales son mejores, luego "regresa" el nuevo tablero.
move_helper(P, [0|T1], [P|T1]).

move_helper(P, [H1|T1], [H1|T2]) :-
    move_helper(P, T1, T2).

% determina el siguiente jugador
nextPlayer(o, x).
nextPlayer(x, o).

%------Dibuja el estado actual del Tablero-----%
drawBoard([A,B,C,D,E,F,G,H,I]):-
	nl, write('\t+---+---+---+'), nl,
	write('\t| '), drawMove(A),
	write('| '), drawMove(B),
	write('| '), drawMove(C), write('|'), nl,
	write('\t+---+---+---+'), nl,
	write('\t| '), drawMove(D),
	write('| '), drawMove(E),
	write('| '), drawMove(F), write('|'), nl,
	write('\t+---+---+---+'), nl,
	write('\t| '), drawMove(G),
	write('| '), drawMove(H),
	write('| '), drawMove(I), write('|'), nl,
	write('\t+---+---+---+'), nl, nl.

% Si Pos es 0, no se movera, imprimira un espacio en blanco
% La ! previene el programa de imprimir el movimiento cuando no se supone que lo haga
drawMove(Pos):-
	Pos = 0, !,
	write('  ').

% Imprime el movimiento en el tablero
drawMove(Pos):-
	write(Pos),
	write(' ').

%----------------------Empezar el juego------------------------------------------------%
% Se empieza el juego poniendo "start."
start:- rules, start_it([x, B, start_it], x).

rules :-
	nl, write('Bienvenido a Tic-Tac-Toe Imposible!'),nl,nl,
	write('Tu (El Humano) seras \'x\''),nl,
	write('La Computadora sera \'o\''),nl,nl,
	write('Para posicionarte, escribe el numero del lugar, seguido de un punto.'),nl,
	drawBoard([1,2,3,4,5,6,7,8,9]).

%----------------Handle start of game and player turns, also checks for win ---------%
% B: Tablero, Player: x or o (checa si ya gano antes de mover), AfterCompBoard: Tablaro despues de que la computadora haga su movimiento
bestMove(P, NP):- minimax(P, NP, _).

% codigo del humano
start_it([x, Board, start_it], x):-
	write('Tu movimiento:'), nl,
	read(Move),
	(
	place_move(Move, x, Board, NewBoard),
	drawBoard(NewBoard),
		(
			win(NewBoard, x), !, write('Tu Ganas!')
			;
			tieGame(_,NewBoard), !, write('Gano El Gato!')
			;
			start_it([o, NewBoard, start_it], o)
		);
		write('-> Movimiento Invalido!'),nl,nl,
    start_it([x, Board, start_it], x)
	).

% codigo de la computadora
start_it([o, Board, start_it], o):-
	write('Movimiento de La Computadora:'), nl,
	bestMove([o, start_it, Board], [x, State, NewBoard]), % busca el mejor camino usando minmax
	drawBoard(NewBoard),
	(
		win(NewBoard, o), !, write('Gana La Computadora!'), nl
		;
		tieGame(_,NewBoard), !, write('Gano El Gato!'), nl
		;
		start_it([x, NewBoard, start_it], x)
	).


%------------- Movimientos para x y o -----------------------%
% interacciona hasta que la posición coincida con el movimiento enviado y luego colocar el movimiento en el tablero
place_move(1, Player_Token, [X|Ls], [Player_Token|Ls]) :- !, X = 0.

place_move(Position, Player_Token, [X|Ls], [X|L2s]) :-
    number(Position),
    Position1 is Position - 1,
    place_move(Position1, Player_Token, Ls, L2s).

%--------------Funciones Ganadoras-----------------------%
win(Matrix, Player):- wColumn(Matrix, Player).
win(Matrix, Player):- wRow(Matrix, Player).
win(Matrix, Player):- wDiagnol(Matrix, Player).

% Condiciones para ganar
% Columnas
wColumn(Matrix, Player) :- Matrix = [Player,_,_,Player,_,_,Player,_,_].
wColumn(Matrix, Player) :- Matrix = [_,Player,_,_,Player,_,_,Player,_].
wColumn(Matrix, Player) :- Matrix = [_,_,Player,_,_,Player,_,_,Player].

% Filas
wRow(Matrix, Player) :- Matrix = [Player,Player,Player,_,_,_,_,_,_].
wRow(Matrix, Player) :- Matrix = [_,_,_,Player,Player,Player,_,_,_].
wRow(Matrix, Player) :- Matrix = [_,_,_,_,_,_,Player,Player,Player].

% Diagonales
wDiagnol(Matrix, Player) :- Matrix = [Player,_,_,_,Player,_,_,_,Player].
wDiagnol(Matrix, Player) :- Matrix = [_,_,Player,_,Player,_,Player,_,_].
