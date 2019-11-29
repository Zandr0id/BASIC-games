AppTitle"Quong"
Graphics 640,480,16,2

timer = CreateTimer(30)

SeedRnd MilliSecs()

.A

;variables
Global player=CreateImage(20,75)
Global badguy=CreateImage(20,75)
Global ball = CreateImage(20,20)
Global stars = LoadImage("stars.bmp") 
Global starsfar=LoadImage("starsfar.bmp")
Global x = 200
Global y = 240
Global badx = 440
Global bady = 240
Global ballx=320
Global bally =Rnd(200,400)
Global ballYvolocity=Rnd(-5,5)
Global ballXvolocity=Rnd(-5,5)
Global score = 0
Global badscore = 0
Global scroly = 0 
;load sound
Global music = LoadSound("SolarSailer.mp3")
Global wallbounce = LoadSound("bounce.wav")
Global badscore2 = LoadSound("badscore.wav")
Global goodscore = LoadSound("goodscore.wav")
Global bounce = LoadSound("wallbounce.wav")
font = LoadFont("Arial",200)
font2 = LoadFont("Arial",20)
SetFont font

LoopSound music
PlaySound music

;make images
SetBuffer ImageBuffer(player)
Color 0,0,255
Rect 0,0,20,75,1
MidHandle player


SetBuffer ImageBuffer(badguy)
Color 255,0,0
Rect 0,0,20,75,1
MidHandle badguy

SetBuffer ImageBuffer(ball)
Color 0,255,0
Oval 0,0,20,20,1
MidHandle ball

Color 255,255,255

SetBuffer BackBuffer()

Text 320,240,"Quong",True,True
Flip 
Delay 3000
SetFont font2

directions()
startlevel()
While Not KeyDown(1)
Cls 
drawstuff()
testkeys()
moveball()
testballcollision()
moveAI()
drawHUD()
checkwin()
WaitTimer timer 
Flip
Wend 

;reset variables
Function startlevel()
x = 100
y = 240
badx = 540
bady = 240
ballx=320
bally =Rnd(200,400)
ballXvolocity=Rnd(-5,5)
ballYvolocity=Rnd(-5,5)
;if the ball has 0 volocity, give it some
If ballXvolocity = 0  
   ballXvolocity = 2
EndIf 

If ballYvolocity = 0 
 ballYvolocity = -4
EndIf 
End Function

;draw every thing
Function drawstuff()

TileImage stars,0,scroly*2
TileImage starsfar,0,scroly
scroly = scroly + 2

If scroly >=ImageHeight(stars)
scroly = 0
EndIf 



DrawImage player,x,y
DrawImage badguy,badx,bady
DrawImage ball,ballx,bally

End Function 

;test the keys
Function testkeys()

If KeyDown(200)
   y = y -7
     If y  <= 42.5 Then
	   y = 42.5
	 EndIf 
EndIf 

If KeyDown(208)
   y = y +7
	  If y  >= 437.5 Then
	    y  = 437.5
	  EndIf 
EndIf 

End Function 


Function moveball()
ballx = ballx + ballXvolocity
bally = bally + ballYvolocity
 
End Function 



Function testballcollision()
testballplayer()
testballbadguy()
testballwalls()
End Function 

;see if it hit you
Function testballplayer()
 If ImagesOverlap(player,x,y,ball,ballx,bally) Then 
    PlaySound bounce 
    ballXvolocity = -ballXvolocity 
		
		If ballYvolocity < 0
		ballYvolocity=Rnd(-5,-1)
		EndIf 
		
		If ballYvolocity = 0
		ballYvolocity = 3
		bollXvolocity = 3
		EndIf 		
		
		If ballYvolocity >0
		ballYvolocity = Rnd(1,5)
		EndIf     

     If ballXvolocity < 0
				ballXvolocity =ballXvolocity-.8
			If ballXvolocity <= -19
			   ballXvolocity = -19
			EndIf 
			EndIf 
			
		  If ballXvolocity > 0
		      ballXvolocity =ballXvolocity + .8
		   If ballXvolocity >= 19
			   ballXvolocity = 19
			EndIf
		EndIf 

  EndIf 
End Function 

;see if it hit the badguy
Function testballbadguy()
 If ImagesOverlap(badguy,badx,bady,ball,ballx,bally) Then 
       PlaySound bounce 
		ballXvolocity = -ballXvolocity
        
		If ballYvolocity < 0
		ballYvolocity=Rnd(-5,-1)
		EndIf 
		
		If ballYvolocity = 0
		ballYvolocity = -3
		bollXvolocity = -3
		EndIf 
		
		If ballYvolocity >0
		ballYvolocity = Rnd(1,5)
		EndIf  
            
		If ballXvolocity < 0
				ballXvolocity =ballXvolocity-.8
			EndIf 
			
		  If ballXvolocity > 0
		      ballXvolocity =ballXvolocity + .8
		   EndIf 
    EndIf   
End Function 

;see if it hit a wall
Function testballwalls()
  If bally <= 10 Or bally>=470
     PlaySound wallbounce
     ballYvolocity = -ballYvolocity
  EndIf 

 If bally <=10 And ballYvolocity =0
   ballYvolocity = Rnd(4,7)
 EndIf 
  If bally >= 470 And ballYvolocity = 0
   ballYvolocity = Rnd(-4,-7)
 EndIf 

;see if he scored
If ballx<0 
PlaySound badscore2
badscore = badscore + 1
Cls
Text 320,240,"Player 2 scored!",1,1
Flip 
Delay 2000
startlevel()
End If 

;see if you scored
If ballx> 640
PlaySound goodscore
score = score +1 
Cls
Text 320,240,"Player 1 scored!",1,1
Flip
Delay 2000
startlevel()
EndIf 
End Function 



Function moveAI()

If bally>bady And ballx >200 Then 
   bady = bady+5
   If bady  >= 437.5 Then
	    bady  = 437.5
	  EndIf 

EndIf 

If bally < bady And ballx >200 Then
   bady = bady-5

 If bady  <= 42.5 Then
	   bady = 42.5
	 EndIf 

EndIf 

If bady>240 And ballx < 200 Then
    bady = bady -5
EndIf

If bady<240 And ballx < 200 Then 
   bady = bady+5
EndIf 

End Function 

Function drawHUD()
Text 50,10,"Player1: " + score,1,1
Text 590,10,"Player2: " +badscore,1,1
End Function 


Function checkwin()

If score = 4 Or badscore = 4
Text 320,240,"Game Point!",1,1
EndIf 

If score >= 5 Then
Cls 
Text 320,240,"Player 1 is the winner!",1,1
Flip
Delay 3000
Cls
End 
EndIf 


If badscore >= 5 Then
Cls
Text 320,240,"Player 2 is the winner!",1,1
Flip
Delay 3000
Cls 
End 
EndIf 

End Function 


Function directions()
Cls 
Repeat 
Locate 0,0
Print "The object of the game is to score 5 points before the other player does."
Print ""
Print "Use the Up and Down arow keys to move your blue paddle."
Print ""
Print "Score points by hitting the ball past the red paddle."
Print ""
Print "The ball will get faster, so good luck!"
Print ""
Print "Hit the spacebar to start."
Until KeyHit(57)
Cls
Text 290,240,"3...",1,1
Flip
Delay 1000
Text 320,240,"2...",1,1
Flip 
Delay 1000 
Text 350,240,"1...",1,1
Flip 
Delay 1000
Text 320,255,"GO!",1,1
Flip 
Delay 1000

End Function 