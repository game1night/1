    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.ArrayList;
    import java.util.Collections;
    
    /**
     * Write a description of class MyWorld here.
     * 
     * @tatatingting (your name) 
     * @v1.0.20190521 (a version number or a date)
     */
    public class Table extends World
    {
        ArrayList<Card> cards = new ArrayList<Card>(); // 扑克牌对象列表
        private int timer = 0;
        
        /**
         * Constructor for objects of class MyWorld.
         * 
         */
        
        // Table类的构造方法
        public Table()
        {    
            // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
            super(600, 400, 1); 
            setBackground("board.png");
            prepare();
        }
        
        public void prepare() {
            putCards();
            drawTitle();
            drawText();
        }
    
        public void drawText() {
            showTime();
            showScore();
        }
    
        public void showTime() {
            showText("Timer: " + timer + "/199", 400, 330);
        }
    
        public void showScore() {
            if (cards.size() > 0) {
                int score = 92;
                for (int i=0;i<cards.size();i++) {
                    score += cards.get(i).getScore();
                }
                showText("Score: " + score + "/100", 400, 360);
                if (score <= 0) {
                    showText("Game Over", 300, 200);
                    Greenfoot.stop();
                }
            }
        }
    
        public void countTime() {
            Greenfoot.delay(10);
            timer++;
            if (timer <= 199) {
                showTime();
            } else {
                showText("Time is out!", 300, 200);
                Greenfoot.stop();
            }
        }
        
        // act()方法是游戏单步执行的动作
        public void act() {
            pairCards();
            countTime();
            showScore();
        }
        
        public void putCards() {
            for (int i = 0; i <= 3; i++) { //在列表中添加两组共2x0123，8张
                cards.add(new Card(i));
                cards.add(new Card(i));
            }
            Collections.shuffle(cards); // 打乱列表中的顺序
            int x = 115, y = 90; // 起点坐标
            for (int i = 0; i < 4; i++) { // 依次序放上几张牌，共两排
                addObject(cards.get(i), x, y);
                addObject(cards.get(i+4), x, y + cards.get(i).getImage().getHeight() + 30);
                x += cards.get(i).getImage().getWidth() + 50;
            }
        }
        
        public void drawTitle() {
            GreenfootImage background = getBackground();
            background.setFont(new Font(true,true,30));
            background.setColor(Color.PINK);
            background.drawString("让卡片翻滚吧！", 40, 380);
            background.setFont(new Font(true,true,49));
            background.setColor(Color.PINK);
            background.drawString("flipCards", 25, 360);
        }
        
        public void pairCards() {
            // 变量
            Card card1 = null, card2 = null; // 用来保存两张对象
            int count = 0; // 表示翻开的是第几张
            int card1Value = 0, card2Value = 0; // 记录两张牌的点数
            // 循环
            for (int i = 0; i < cards.size(); i++) { // 用for循环遍历cards中所有牌
                if (cards.get(i).getFaceUp()) { // 寻找翻开的牌
                    count++; // 记下是第几张翻开的牌
                    if (count==1) { // 如果是翻开的第1张
                        card1 = cards.get(i);
                        card1Value = card1.getValue();
                    } else if (count==2) { // 如果是翻开的第2张
                        card2 = cards.get(i);
                        card2Value = card2.getValue();
                        if (card1Value == card2Value) { // 如果值相同
                            Greenfoot.playSound("45.wav");
                            // 延迟10ms,增强体验
                            Greenfoot.delay(15);
                            // 移除两张牌
                            removeObject(card1);
                            removeObject(card2);
                            cards.remove(card1);
                            cards.remove(card2);
                            if (cards.size() == 0) {
                                showText("Well done!", 300, 200);
                                Greenfoot.stop();
                            }
                        } else { // 如果值不同
                            Greenfoot.delay(15);
                            card1.turnFaceDown();
                            card2.turnFaceDown();
                        }
                        break; // 剩下的不再便利，结束for循环
                    } else {
                        showText("sth. wrong! pls reset", 50, 330);
                    }
                }
            }  
        }
    
}
