//
//  PlayingCard.h
//  
//
//  Created by Henri Juvonen on 2/17/13.
//
//

#import <Card.h>

@interface PlayingCard : Card

@property (strong, nonatomic) NSString *suit;
@property (nonatomic) NSUInteger rank;

+ (NSArray *)validSuits;
+ (NSUInteger)maxRank;

@end
