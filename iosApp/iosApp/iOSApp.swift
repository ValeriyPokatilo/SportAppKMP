import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        checkFirstLaunch()
    }
    
    var body: some Scene {
        WindowGroup {
            MainScreen()
        }
    }
    
    func checkFirstLaunch() {
        let factory = KmpSettingsFactory()
        let settings = factory.isFirstLaunch()
        let manager = FirstLaunchManager(settings: settings)
        
        if manager.checkFirstLaunch() {
            DemoWorkouts.shared.createDemoWorkouts(fileManager: Shared.FileManager())
        }
    }
}
