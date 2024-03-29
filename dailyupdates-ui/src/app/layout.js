import Navbar from '@/components/Navbar'
import './globals.css'
import Footer from '@/components/Footer'
import { roboto } from "@/styles/fonts";

export const metadata = {
  title: 'DailyUpdates',
  description: 'DailyUpdates',
}

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <div className={`flex flex-col min-h-screen ${roboto.className}`}>
          <header>
            <Navbar />
          </header>

          <main className="flex-1">{children}</main>
          
          <Footer />
        </div>
      </body>
    </html>
  )
}
