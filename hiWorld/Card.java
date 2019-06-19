import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor
{
    /**
     * Act - do whatever the card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // 字段
    private int value = -1; // 初始点数-1，表示还没有生成确定的牌
    private boolean isFaceUp = false; // 若真，则正面朝上
    private GreenfootImage faceUpImage = null; // 正面图
    private GreenfootImage faceDownImage = null; // 背面图
    private int score = 1; // 初试分数
    
    // Card类的构造方法
    public Card(int cardValue) { // cardValue是创建时传入的点数
        value = cardValue;
        isFaceUp = false; // 创建时是背上
        String fileName = value + ".png";
        faceUpImage = new GreenfootImage(fileName.toLowerCase()); // 正面图，处理文件名
        faceDownImage = new GreenfootImage("back.png"); // 背面图
        setImage(faceDownImage); // 创建时是背上
        score = 1; // 分数
    }
    // 获取这张牌的点数
    public int getValue() {
        return value;
    }
    
    public int getScore() {
        return score;
    }
    
    // 获取这张牌是否已翻面
    public boolean getFaceUp() {
        return isFaceUp;
    }
    // 将牌翻成背面上
    public void turnFaceDown() {
        isFaceUp = false;
        setImage(faceDownImage);
    }
    
    // act()方法是游戏单步执行的动作
    public void act() {
        // Add your action code here.
        if (Greenfoot.mouseClicked(this)) { // 如果鼠标单击了这张牌
            if (!isFaceUp) { // 如果背面上
                setImage(faceUpImage); // 换成正面上
                //new GreenfootSound("click.wav").play();
                Greenfoot.playSound("click.wav");
                isFaceUp = true;
            }
            // 统计分数
            this.score--;
            this.score--;
            this.score--;
        }
    }   
}
