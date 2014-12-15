#import <UIKit/UIKit.h>
#import "M2UController.h"

@interface MaybankPaymentController :UIViewController<M2UDelegate> {
	M2UController *paymentController;
}

@property(nonatomic,retain) M2UController *paymentController;

@end