import Navbar from '@/components/Navbar'
import './globals.css'

export const metadata = {
  title: 'DailyUpdates',
  description: 'DailyUpdates',
}

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <div className="flex flex-col min-h-screen">
          <header>
            <Navbar />
          </header>

          <main className="flex-1">{children}</main>

        </div>
      </body>
    </html>
  )
}
