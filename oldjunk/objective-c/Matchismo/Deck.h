//
//  Deck.h
//  
//
//  Created by Henri Juvonen on 2/17/13.
//
// Stanford Course - Code together iOS

#import <Foundation/Foundation.h>
#import "Card.h"

@interface Deck : NSObject

- (void)addCard:(Card *)card atTop:(BOOL)atTop;

-(Card *)drawRandomCard;

@end
