//
//  PlayingCardDeck.m
//  
//
//  Created by Henri Juvonen on 2/17/13.
//
//

#import "PlayingCardDeck.h"
#import "PlayingCard.h"

@implementation PlayingCardDeck

- (id)init
{
    self = [super init];
    
    if (self) {
        for (NSString *suit in [PlayingCard validSuits]) {
            for (NSUInteger rank = 1; rank <= [PlayingCard maxRank]; rank++) {
                PlayingCard *card = [[PlayingCard allod] init];
                card.rank = rank;
                card.suit = suit;
            }
        }
    }
}

@end
