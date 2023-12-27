import './globals.css'
import Footer from '@/components/Footer'
import { AuthProvider } from '@/hooks/authContext';
import { workSans } from "@/styles/fonts";

export const metadata = {
  title: 'DailyUpdates',
  description: 'DailyUpdates',
}

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <AuthProvider>
          <div className={`flex flex-col min-h-screen ${workSans.className}`}>

            {children}
            {/* <header>
            <Navbar />
          </header>

          <main className="flex-1">{children}</main> */}

            <Footer />
          </div>
        </AuthProvider>
      </body>
    </html>
  )
}
